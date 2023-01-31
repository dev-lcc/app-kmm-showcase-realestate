package devlcc.io.kmmshowcaserealestate.core.data.repository

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesIgnore
import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import kotlinx.coroutines.flow.Flow

interface PropertiesRepository {

    /**
     * Async fetch list of properties by status.
     */
    suspend fun getPropertiesByStatus(
        status: Property.Status, offset: Int, limit: Int, sort: Property.Sort
    ): List<Property>

    /**
     * Reactive fetch list of properties by status.
     */
    @NativeCoroutines
    fun getPropertiesByStatusStream(
        status: Property.Status, offset: Int, limit: Int, sort: Property.Sort
    ): Flow<List<Property>>

    /**
     * Async fetch list of properties by type.
     */
    suspend fun getPropertiesByType(
        type: Property.Type, offset: Int, limit: Int, sort: Property.Sort
    ): List<Property>

    /**
     * Reactive fetch list of properties by type.
     */
    @NativeCoroutines
    fun getPropertiesByTypeStream(
        type: Property.Type, offset: Int, limit: Int, sort: Property.Sort
    ): Flow<List<Property>>

    /**
     * Async fetch Property by ID.
     */
    suspend fun getProperty(propertyId: String): Property

    /**
     * Reactive fetch Property by ID
     */
    @NativeCoroutines
    fun getPropertyStream(propertyId: String): Flow<Property>

    /**
     * Async search properties by keyword.
     */
    suspend fun searchProperties(
        keyword: String, offset: Int, limit: Int, sort: Property.Sort
    ): List<Property>

    /**
     * Reactive search properties by keyword.
     */
    @NativeCoroutines
    fun searchPropertiesStream(
        keyword: String, offset: Int, limit: Int, sort: Property.Sort
    ): Flow<List<Property>>

}