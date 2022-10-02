package devlcc.io.kmmshowcaserealestate.core.database.sqldelight.dao

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import devlcc.io.kmmshowcaserealestate.core.database.KMMShowcaseRealEstateDb
import devlcc.io.kmmshowcaserealestate.core.database.PropertyDao
import devlcc.io.kmmshowcaserealestate.core.database.sqldelight.entity.Embedded
import devlcc.io.kmmshowcaserealestate.core.database.sqldelight.entity.toEmbedded
import devlcc.io.kmmshowcaserealestate.core.database.sqldelight.entity.toModel
import devlcc.io.kmmshowcaserealestate.core.model.property.Agent
import devlcc.io.kmmshowcaserealestate.core.model.property.OpenHouse
import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import devlcc.io.kmmshowcaserealestate.core.database.Property as PropertyEntity

class PropertyDaoImpl(
    private val database: KMMShowcaseRealEstateDb,
    private val json: Json,
) : PropertyDao {

    override suspend fun getPropertiesByStatus(
        status: Property.Status, offset: Int, limit: Int, sort: Property.Sort
    ): List<Property> = when (sort.order) {
        Property.Sort.Order.Ascending -> database.propertyQueries.getPropertiesByStatusASC(
            propStatus = json.encodeToString(
                Embedded.Status.serializer(),
                status.toEmbedded(),
            ),
            sortField = sort.field.value,
            page = (offset / limit).toLong(),
            docsPerPage = limit.toLong(),
        )
        Property.Sort.Order.Descending -> database.propertyQueries.getPropertiesByStatusDESC(
            propStatus = json.encodeToString(
                Embedded.Status.serializer(),
                status.toEmbedded(),
            ),
            sortField = sort.field.value,
            page = (offset / limit).toLong(),
            docsPerPage = limit.toLong(),
        )
    }.executeAsList().map { entity ->
        entity.toModel(json)
    }

    override fun getPropertiesByStatusStream(
        status: Property.Status, offset: Int, limit: Int, sort: Property.Sort
    ): Flow<List<Property>> = when (sort.order) {
        Property.Sort.Order.Ascending -> database.propertyQueries.getPropertiesByStatusASC(
            propStatus = json.encodeToString(
                Embedded.Status.serializer(),
                status.toEmbedded(),
            ),
            sortField = sort.field.value,
            page = (offset / limit).toLong(),
            docsPerPage = limit.toLong(),
        )
        Property.Sort.Order.Descending -> database.propertyQueries.getPropertiesByStatusDESC(
            propStatus = json.encodeToString(
                Embedded.Status.serializer(),
                status.toEmbedded(),
            ),
            sortField = sort.field.value,
            page = (offset / limit).toLong(),
            docsPerPage = limit.toLong(),
        )
    }.asFlow().mapToList().map { entities ->
        entities.map { entity ->
            entity.toModel(json)
        }
    }

    override suspend fun getPropertiesByType(
        type: Property.Type, offset: Int, limit: Int, sort: Property.Sort
    ): List<Property> = when (sort.order) {
        Property.Sort.Order.Ascending -> database.propertyQueries.getPropertiesByTypeASC(
            propType = json.encodeToString(
                Embedded.Type.serializer(),
                type.toEmbedded(),
            ),
            sortField = sort.field.value,
            page = (offset / limit).toLong(),
            docsPerPage = limit.toLong(),
        )
        Property.Sort.Order.Descending -> database.propertyQueries.getPropertiesByStatusDESC(
            propStatus = json.encodeToString(
                Embedded.Type.serializer(),
                type.toEmbedded(),
            ),
            sortField = sort.field.value,
            page = (offset / limit).toLong(),
            docsPerPage = limit.toLong(),
        )
    }.executeAsList().map { entity ->
        entity.toModel(json)
    }

    override fun getPropertiesByTypeStream(
        type: Property.Type, offset: Int, limit: Int, sort: Property.Sort
    ): Flow<List<Property>> = when (sort.order) {
        Property.Sort.Order.Ascending -> database.propertyQueries.getPropertiesByTypeASC(
            propType = json.encodeToString(
                Embedded.Type.serializer(),
                type.toEmbedded(),
            ),
            sortField = sort.field.value,
            page = (offset / limit).toLong(),
            docsPerPage = limit.toLong(),
        )
        Property.Sort.Order.Descending -> database.propertyQueries.getPropertiesByStatusDESC(
            propStatus = json.encodeToString(
                Embedded.Type.serializer(),
                type.toEmbedded(),
            ),
            sortField = sort.field.value,
            page = (offset / limit).toLong(),
            docsPerPage = limit.toLong(),
        )
    }.asFlow().mapToList().map { entities ->
        entities.map { entity ->
            entity.toModel(json)
        }
    }

    override suspend fun searchProperties(
        keyword: String, offset: Int, limit: Int, sort: Property.Sort
    ): List<Property> = when (sort.order) {
        Property.Sort.Order.Ascending -> database.propertyQueries.searchPropertiesASC(
            keyword = keyword,
            sortField = sort.field.value,
            page = (offset / limit).toLong(),
            docsPerPage = limit.toLong(),
        )
        Property.Sort.Order.Descending -> database.propertyQueries.searchPropertiesDESC(
            keyword = keyword,
            sortField = sort.field.value,
            page = (offset / limit).toLong(),
            docsPerPage = limit.toLong(),
        )
    }.executeAsList().map { entity ->
        entity.toModel(json)
    }

    override fun searchPropertiesStream(
        keyword: String, offset: Int, limit: Int, sort: Property.Sort
    ): Flow<List<Property>> = when (sort.order) {
        Property.Sort.Order.Ascending -> database.propertyQueries.searchPropertiesASC(
            keyword = keyword,
            sortField = sort.field.value,
            page = (offset / limit).toLong(),
            docsPerPage = limit.toLong(),
        )
        Property.Sort.Order.Descending -> database.propertyQueries.searchPropertiesDESC(
            keyword = keyword,
            sortField = sort.field.value,
            page = (offset / limit).toLong(),
            docsPerPage = limit.toLong(),
        )
    }.asFlow().mapToList().map { entities ->
        entities.map { entity ->
            entity.toModel(json)
        }
    }

    override fun getProperty(propertyId: String): Flow<Property> =
        database.propertyQueries.getProperty(propertyId).asFlow<PropertyEntity>().mapToOne()
            .map { it.toModel(json) }

    override suspend fun upsert(property: Property) {
        database.transactionWithResult {
            database.propertyQueries.upsertProperty(propertyID = property.propertyID!!,
                listingID = property.listingID,
                products = json.encodeToString(
                    ListSerializer(Embedded.Product.serializer()),
                    property.products.map(Property.Product::toEmbedded)
                ),
                rdcWebURL = property.rdcWebURL,
                propType = property.propType?.let {
                    json.encodeToString(
                        Embedded.Type.serializer(),
                        it.toEmbedded(),
                    )
                },
                propSubType = property.propSubType?.let {
                    json.encodeToString(
                        Embedded.SubType.serializer(),
                        it.toEmbedded(),
                    )
                },
                virtualTour = property.virtualTour?.let {
                    json.encodeToString(
                        Embedded.VirtualTour.serializer(),
                        it.toEmbedded(),
                    )
                },
                address = property.address?.let {
                    json.encodeToString(
                        Embedded.Address.serializer(),
                        it.toEmbedded(),
                    )
                },
                branding = property.branding?.let {
                    json.encodeToString(
                        Embedded.Branding.serializer(),
                        it.toEmbedded(),
                    )
                },
                propStatus = property.propStatus?.let {
                    json.encodeToString(
                        Embedded.Status.serializer(),
                        it.toEmbedded(),
                    )
                },
                price = property.price,
                bathsFull = property.bathsFull?.toLong(),
                baths = property.baths?.toLong(),
                beds = property.beds?.toLong(),
                buildingSize = property.buildingSize?.let {
                    json.encodeToString(
                        Embedded.BuildingSize.serializer(),
                        it.toEmbedded(),
                    )
                },
                openHouses = json.encodeToString(
                    ListSerializer(Embedded.OpenHouse.serializer()),
                    property.openHouses.map(OpenHouse::toEmbedded)
                ),
                agents = json.encodeToString(
                    ListSerializer(Embedded.Agent.serializer()),
                    property.agents.map(Agent::toEmbedded)
                ),
                office = property.office?.let {
                    json.encodeToString(
                        Embedded.Office.serializer(),
                        it.toEmbedded(),
                    )
                },
                lastUpdate = property.lastUpdate,
                clientDisplayFlags = property.clientDisplayFlags?.let {
                    json.encodeToString(
                        Embedded.ClientDisplayFlags.serializer(),
                        it.toEmbedded(),
                    )
                },
                leadForms = property.leadForms?.let {
                    json.encodeToString(
                        Embedded.LeadForms.serializer(),
                        it.toEmbedded(),
                    )
                },
                photoCount = property.photoCount?.toLong(),
                thumbnail = property.thumbnail,
                pageNo = property.pageNo?.toLong(),
                rank = property.rank,
                listTracking = property.listTracking,
                mls = property.mls?.let {
                    json.encodeToString(
                        Embedded.MultipleListingService.serializer(),
                        it.toEmbedded(),
                    )
                },
                dataSourceName = property.dataSourceName?.let {
                    json.encodeToString(
                        Embedded.MultipleListingService.Type.serializer(),
                        it.toEmbedded(),
                    )
                })
        }
    }
}