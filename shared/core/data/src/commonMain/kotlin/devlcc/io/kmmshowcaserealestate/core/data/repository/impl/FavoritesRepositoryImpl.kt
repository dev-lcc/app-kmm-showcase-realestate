package devlcc.io.kmmshowcaserealestate.core.data.repository.impl

import devlcc.io.kmmshowcaserealestate.core.data.repository.FavoritesRepository
import devlcc.io.kmmshowcaserealestate.core.database.FavoriteDao
import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import kotlinx.coroutines.flow.Flow

class FavoritesRepositoryImpl(
    private val favoriteDao: FavoriteDao,
) : FavoritesRepository {

    override suspend fun getFavorites(
        offset: Int, limit: Int, sort: Property.Sort
    ): List<Property> {
        return favoriteDao.getFavoriteProperties(
            offset = offset,
            limit = limit,
            sort = sort,
        )
    }

    override fun getFavoritesStream(
        offset: Int, limit: Int, sort: Property.Sort
    ): Flow<List<Property>> {
        return favoriteDao.getFavoritePropertiesStream(
            offset = offset,
            limit = limit,
            sort = sort,
        )
    }

    override suspend fun addToFavorites(which: Property) {
        favoriteDao.addToFavorites(which)
    }

    override suspend fun removeFromFavorites(which: Property) {
        favoriteDao.removeFromFavorites(which)
    }
}