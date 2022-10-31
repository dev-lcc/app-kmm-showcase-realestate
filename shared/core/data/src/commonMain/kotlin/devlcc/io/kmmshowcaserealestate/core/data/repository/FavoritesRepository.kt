package devlcc.io.kmmshowcaserealestate.core.data.repository

import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    /**
     * Async fetch my favorite property list.
     */
    suspend fun getFavorites(offset: Int, limit: Int, sort: Property.Sort): List<Property>

    /**
     * Reactive fetch my favorite property list.
     */
    fun getFavoritesStream(offset: Int, limit: Int, sort: Property.Sort): Flow<List<Property>>

    /**
     * Add property to my favorite list.
     */
    suspend fun addToFavorites(which: Property)

    /**
     * Remove property from my favorite list.
     */
    suspend fun removeFromFavorites(which: Property)

}