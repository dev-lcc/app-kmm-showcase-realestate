package devlcc.io.kmmshowcaserealestate.core.database

import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import kotlinx.coroutines.flow.Flow

interface FavoriteDao {

    suspend fun getFavoriteProperties(
        offset: Int, limit: Int, sort: Property.Sort = Property.Sort.Default
    ): List<Property>

    fun getFavoritePropertiesStream(
        offset: Int, limit: Int, sort: Property.Sort = Property.Sort.Default
    ): Flow<List<Property>>

    suspend fun addToFavorites(which: Property)

    suspend fun removeFromFavorites(which: Property)

}