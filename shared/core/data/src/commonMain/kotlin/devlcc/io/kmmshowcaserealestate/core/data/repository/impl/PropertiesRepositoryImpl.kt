package devlcc.io.kmmshowcaserealestate.core.data.repository.impl

import devlcc.io.kmmshowcaserealestate.core.data.repository.PropertiesRepository
import devlcc.io.kmmshowcaserealestate.core.database.PropertyDao
import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import devlcc.io.kmmshowcaserealestate.core.network.PropertiesApiService
import kotlinx.coroutines.flow.Flow

class PropertiesRepositoryImpl(
    private val propertiesApiService: PropertiesApiService,
    private val propertiesLocalDao: PropertyDao
) : PropertiesRepository {

    override suspend fun getPropertiesByType(
        type: Property.Type,
        offset: Int,
        limit: Int,
        sort: Property.Sort
    ): List<Property> {
        return propertiesLocalDao.getPropertiesByType(
            type = type,
            offset = offset,
            limit = limit,
            sort = sort
        )
    }

    override fun getPropertiesByTypeStream(
        type: Property.Type,
        offset: Int,
        limit: Int,
        sort: Property.Sort
    ): Flow<List<Property>> {
        return propertiesLocalDao.getPropertiesByTypeStream(
            type = type,
            offset = offset,
            limit = limit,
            sort = sort
        )
    }

    override fun getProperty(propertyId: String): Flow<Property> {
        return propertiesLocalDao.getProperty(propertyId)
    }

    override suspend fun searchProperties(
        keyword: String,
        offset: Int,
        limit: Int,
        sort: Property.Sort
    ): List<Property> {
        return propertiesLocalDao.searchProperties(
            keyword = keyword,
            offset = offset,
            limit = limit,
            sort = sort,
        )
    }

    override fun searchPropertiesStream(
        keyword: String,
        offset: Int,
        limit: Int,
        sort: Property.Sort
    ): Flow<List<Property>> {
        return propertiesLocalDao.searchPropertiesStream(
            keyword = keyword,
            offset = offset,
            limit = limit,
            sort = sort,
        )
    }
}