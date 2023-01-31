package devlcc.io.kmmshowcaserealestate.core.database.sqldelight.dao

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import devlcc.io.kmmshowcaserealestate.core.database.KMMShowcaseRealEstateDb
import devlcc.io.kmmshowcaserealestate.core.database.PropertyDao
import devlcc.io.kmmshowcaserealestate.core.database.sqldelight.entity.Embedded
import devlcc.io.kmmshowcaserealestate.core.database.sqldelight.entity.toEmbedded
import devlcc.io.kmmshowcaserealestate.core.database.sqldelight.entity.toModel
import devlcc.io.kmmshowcaserealestate.core.database.sqldelight.transactionWithContext
import devlcc.io.kmmshowcaserealestate.core.model.property.Agent
import devlcc.io.kmmshowcaserealestate.core.model.property.OpenHouse
import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class PropertyDaoImpl(
    private val database: KMMShowcaseRealEstateDb,
    private val json: Json,
    private val ioDispatcher: CoroutineDispatcher,
) : PropertyDao {

    override suspend fun getPropertiesByStatus(
        status: Property.Status, offset: Int, limit: Int, sort: Property.Sort
    ): List<Property> = withContext(ioDispatcher) {
        when (sort.order) {
            Property.Sort.Order.Ascending -> database.propertyQueries.getPropertiesByStatusASC(
                propStatus = json.encodeToString(
                    Embedded.Status.serializer(),
                    status.toEmbedded(),
                ),
                sortField = sort.field.value,
                offset = offset.toLong(),
                docsPerPage = limit.toLong(),
            )
            Property.Sort.Order.Descending -> database.propertyQueries.getPropertiesByStatusDESC(
                propStatus = json.encodeToString(
                    Embedded.Status.serializer(),
                    status.toEmbedded(),
                ),
                sortField = sort.field.value,
                offset = offset.toLong(),
                docsPerPage = limit.toLong(),
            )
        }.executeAsList().map { entity ->
            entity.toModel(json)
        }
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
            offset = offset.toLong(),
            docsPerPage = limit.toLong(),
        )
        Property.Sort.Order.Descending -> database.propertyQueries.getPropertiesByStatusDESC(
            propStatus = json.encodeToString(
                Embedded.Status.serializer(),
                status.toEmbedded(),
            ),
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

    override suspend fun getPropertiesByType(
        type: Property.Type, offset: Int, limit: Int, sort: Property.Sort
    ): List<Property> = withContext(ioDispatcher) {
        when (sort.order) {
            Property.Sort.Order.Ascending -> database.propertyQueries.getPropertiesByTypeASC(
                propType = json.encodeToString(
                    Embedded.Type.serializer(),
                    type.toEmbedded(),
                ),
                sortField = sort.field.value,
                offset = offset.toLong(),
                docsPerPage = limit.toLong(),
            )
            Property.Sort.Order.Descending -> database.propertyQueries.getPropertiesByTypeDESC(
                propType = json.encodeToString(
                    Embedded.Type.serializer(),
                    type.toEmbedded(),
                ),
                sortField = sort.field.value,
                offset = offset.toLong(),
                docsPerPage = limit.toLong(),
            )
        }.executeAsList().map { entity ->
            entity.toModel(json)
        }
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
            offset = offset.toLong(),
            docsPerPage = limit.toLong(),
        )
        Property.Sort.Order.Descending -> database.propertyQueries.getPropertiesByTypeDESC(
            propType = json.encodeToString(
                Embedded.Type.serializer(),
                type.toEmbedded(),
            ),
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

    override suspend fun searchProperties(
        keyword: String, offset: Int, limit: Int, sort: Property.Sort
    ): List<Property> = withContext(ioDispatcher) {
        when (sort.order) {
            Property.Sort.Order.Ascending -> database.propertyQueries.searchPropertiesASC(
                keyword = keyword,
                sortField = sort.field.value,
                offset = offset.toLong(),
                docsPerPage = limit.toLong(),
            )
            Property.Sort.Order.Descending -> database.propertyQueries.searchPropertiesDESC(
                keyword = keyword,
                sortField = sort.field.value,
                offset = offset.toLong(),
                docsPerPage = limit.toLong(),
            )
        }.executeAsList().map { entity ->
            entity.toModel(json)
        }
    }

    override fun searchPropertiesStream(
        keyword: String, offset: Int, limit: Int, sort: Property.Sort
    ): Flow<List<Property>> = when (sort.order) {
        Property.Sort.Order.Ascending -> database.propertyQueries.searchPropertiesASC(
            keyword = keyword,
            sortField = sort.field.value,
            offset = offset.toLong(),
            docsPerPage = limit.toLong(),
        )
        Property.Sort.Order.Descending -> database.propertyQueries.searchPropertiesDESC(
            keyword = keyword,
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

    override suspend fun getProperty(propertyId: String): Property =
        database.propertyQueries.getProperty(propertyId)
            .executeAsOne()
            .toModel(json)

    override fun getPropertyStream(propertyId: String): Flow<Property> =
        database.propertyQueries.getProperty(propertyId)
            .asFlow()
            .flowOn(ioDispatcher)
            .mapToOne()
            .map { it.toModel(json) }

    override suspend fun upsert(property: Property) {
        database.transactionWithContext(ioDispatcher) {
            internalUpsert(property)
        }
    }

    override suspend fun upsert(batchCount: Int, vararg properties: Property) {

        properties.toList().chunked(batchCount).forEach { batchList ->
            // Create a DB transaction to execute ${batchCount} upsert operation
            database.transactionWithContext(ioDispatcher) {
                batchList.forEach { property ->
                    internalUpsert(property)
                }
            }
        }
    }

    private fun internalUpsert(property: Property) {
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
                ListSerializer(Embedded.Agent.serializer()), property.agents.map(Agent::toEmbedded)
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