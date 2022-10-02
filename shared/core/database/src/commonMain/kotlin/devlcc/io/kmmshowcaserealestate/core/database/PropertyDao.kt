package devlcc.io.kmmshowcaserealestate.core.database

import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import kotlinx.coroutines.flow.Flow

interface PropertyDao {

    suspend fun getPropertiesByStatus(
        status: Property.Status,
        offset: Int,
        limit: Int,
        sort: Property.Sort = Property.Sort.Default
    ): List<Property>

    fun getPropertiesByStatusStream(
        status: Property.Status,
        offset: Int,
        limit: Int,
        sort: Property.Sort = Property.Sort.Default
    ): Flow<List<Property>>

    suspend fun getPropertiesByType(
        type: Property.Type, offset: Int, limit: Int, sort: Property.Sort = Property.Sort.Default
    ): List<Property>

    fun getPropertiesByTypeStream(
        type: Property.Type, offset: Int, limit: Int, sort: Property.Sort = Property.Sort.Default
    ): Flow<List<Property>>

    suspend fun searchProperties(
        keyword: String, offset: Int, limit: Int, sort: Property.Sort = Property.Sort.Default
    ): List<Property>

    fun searchPropertiesStream(
        keyword: String, offset: Int, limit: Int, sort: Property.Sort = Property.Sort.Default
    ): Flow<List<Property>>

    fun getProperty(propertyId: String): Flow<Property>

    suspend fun upsert(property: Property)

}