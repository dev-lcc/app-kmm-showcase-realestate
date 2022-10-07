package devlcc.io.kmmshowcaserealestate.core.data.repository

import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import kotlinx.coroutines.flow.Flow

interface PropertiesRepository {

    /**
     * Async fetch list of properties.
     */
    suspend fun getPropertiesByType(
        type: Property.Type, offset: Int, limit: Int, sort: Property.Sort
    ): List<Property>

    /**
     * Reactive fetch list of properties
     */
    fun getPropertiesByTypeStream(
        type: Property.Type, offset: Int, limit: Int, sort: Property.Sort
    ): Flow<List<Property>>

    /**
     * Reactive fetch Property by ID
     */
    fun getProperty(propertyId: String): Flow<Property>

    /**
     * Async search properties by keyword.
     */
    suspend fun searchProperties(
        keyword: String, offset: Int, limit: Int, sort: Property.Sort
    ): List<Property>

    /**
     * Reactive search properties by keyword.
     */
    fun searchPropertiesStream(
        keyword: String, offset: Int, limit: Int, sort: Property.Sort
    ): Flow<List<Property>>

}