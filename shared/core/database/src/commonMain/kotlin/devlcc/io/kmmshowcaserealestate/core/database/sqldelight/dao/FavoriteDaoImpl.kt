package devlcc.io.kmmshowcaserealestate.core.database.sqldelight.dao

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import devlcc.io.kmmshowcaserealestate.core.database.FavoriteDao
import devlcc.io.kmmshowcaserealestate.core.database.KMMShowcaseRealEstateDb
import devlcc.io.kmmshowcaserealestate.core.database.sqldelight.entity.toModel
import devlcc.io.kmmshowcaserealestate.core.database.sqldelight.transactionWithContext
import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json

class FavoriteDaoImpl(
    private val database: KMMShowcaseRealEstateDb,
    private val json: Json,
    private val ioDispatcher: CoroutineDispatcher,
) : FavoriteDao {

    override suspend fun getFavoriteProperties(
        offset: Int, limit: Int, sort: Property.Sort
    ): List<Property> = withContext(ioDispatcher) {
        when (sort.order) {
            Property.Sort.Order.Ascending -> database.favoriteQueries.getFavoritesASC(
                sortField = sort.field.value,
                offset = offset.toLong(),
                docsPerPage = limit.toLong(),
            )
            Property.Sort.Order.Descending -> database.favoriteQueries.getFavoritesDESC(
                sortField = sort.field.value,
                offset = offset.toLong(),
                docsPerPage = limit.toLong(),
            )
        }.executeAsList().map { entity ->
            entity.toModel(json)
        }
    }

    override fun getFavoritePropertiesStream(
        offset: Int, limit: Int, sort: Property.Sort
    ): Flow<List<Property>> = when (sort.order) {
        Property.Sort.Order.Ascending -> database.favoriteQueries.getFavoritesASC(
            sortField = sort.field.value,
            offset = offset.toLong(),
            docsPerPage = limit.toLong(),
        )
        Property.Sort.Order.Descending -> database.favoriteQueries.getFavoritesDESC(
            sortField = sort.field.value,
            offset = offset.toLong(),
            docsPerPage = limit.toLong(),
        )
    }
        .asFlow()
        .flowOn(ioDispatcher)
        .mapToList()
        .map { entities ->
            entities.map { entity ->
                entity.toModel(json)
            }
        }

    override suspend fun addToFavorites(which: Property) {
        val propertyID = which.propertyID ?: return
        database.transactionWithContext(ioDispatcher) {
            database.favoriteQueries.addToFavorites(
                propertyID = propertyID,
                createdAt = Clock.System.now().toString()
            )
        }
    }

    override suspend fun removeFromFavorites(which: Property) {
        val propertyID = which.propertyID ?: return
        database.transactionWithContext(ioDispatcher) {
            database.favoriteQueries.removeFromFavorites(
                propertyID = propertyID,
            )
        }
    }
}