package devlcc.io.kmmshowcaserealestate.core.database.sqldelight.entity

import devlcc.io.kmmshowcaserealestate.core.model.property.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import devlcc.io.kmmshowcaserealestate.core.database.Property as PropertyEntity

fun PropertyEntity.toModel(
    json: Json,
): Property = Property(propertyID = this.propertyID,
    listingID = this.listingID,
    products = this.products?.let { str ->
        return@let try {
            json.decodeFromString(
                ListSerializer(Embedded.Product.serializer()), str
            ).map(Embedded.Product::toModel)
        } catch (err: Throwable) {
            emptyList()
        }
    } ?: emptyList(),
    rdcWebURL = this.rdcWebURL,
    propType = this.propType?.let { str ->
        return@let try {
            json.decodeFromString(Embedded.Type.serializer(), str).toModel()
        } catch (err: Throwable) {
            null
        }
    },
    propSubType = this.propSubType?.let { str ->
        return@let try {
            json.decodeFromString(Embedded.SubType.serializer(), str).toModel()
        } catch (err: Throwable) {
            null
        }
    },
    virtualTour = this.virtualTour?.let { str ->
        return@let try {
            val embedded = json.decodeFromString(Embedded.VirtualTour.serializer(), str)
            embedded.toModel()
        } catch (err: Throwable) {
            null
        }
    },
    address = this.address?.let { str ->
        return@let try {
            val embedded = json.decodeFromString(Embedded.Address.serializer(), str)
            embedded.toModel()
        } catch (err: Throwable) {
            null
        }
    },
    branding = this.branding?.let { str ->
        return@let try {
            val embedded = json.decodeFromString(Embedded.Branding.serializer(), str)
            embedded.toModel()
        } catch (err: Throwable) {
            null
        }
    },
    propStatus = this.propStatus?.let { str ->
        return@let try {
            json.decodeFromString(Embedded.Status.serializer(), str).toModel()
        } catch (err: Throwable) {
            null
        }
    },
    price = this.price,
    bathsFull = this.bathsFull?.toInt(),
    baths = this.baths?.toInt(),
    beds = this.beds?.toInt(),
    buildingSize = this.buildingSize?.let { str ->
        try {
            val embedded = json.decodeFromString(Embedded.BuildingSize.serializer(), str)
            embedded.toModel()
        } catch (err: Throwable) {
            null
        }
    },
    openHouses = this.openHouses?.let { str ->
        try {
            json.decodeFromString(
                ListSerializer(Embedded.OpenHouse.serializer()), str
            ).map(Embedded.OpenHouse::toModel)
        } catch (err: Throwable) {
            emptyList()
        }
    } ?: emptyList(),
    agents = this.agents?.let { str ->
        return@let try {
            json.decodeFromString(
                ListSerializer(Embedded.Agent.serializer()), str
            ).map(Embedded.Agent::toModel)
        } catch (err: Throwable) {
            emptyList()
        }
    } ?: emptyList(),
    office = this.office?.let { str ->
        return@let try {
            val embedded = json.decodeFromString(Embedded.Office.serializer(), str)
            embedded.toModel()
        } catch (err: Throwable) {
            null
        }
    },
    lastUpdate = this.lastUpdate,
    clientDisplayFlags = this.clientDisplayFlags?.let { str ->
        return@let try {
            val embedded = json.decodeFromString(Embedded.ClientDisplayFlags.serializer(), str)
            embedded.toModel()
        } catch (err: Throwable) {
            null
        }
    },
    leadForms = this.leadForms?.let { str ->
        return@let try {
            val embedded = json.decodeFromString(Embedded.LeadForms.serializer(), str)
            embedded.toModel()
        } catch (err: Throwable) {
            null
        }
    },
    photoCount = this.photoCount?.toInt(),
    thumbnail = this.thumbnail,
    pageNo = this.pageNo?.toInt(),
    rank = this.rank,
    listTracking = this.listTracking,
    mls = this.mls?.let { str ->
        return@let try {
            val embedded = json.decodeFromString(Embedded.MultipleListingService.serializer(), str)
            embedded.toModel()
        } catch (err: Throwable) {
            null
        }
    },
    dataSourceName = this.dataSourceName?.let { str ->
        return@let try {
            json.decodeFromString(
                Embedded.MultipleListingService.Type.serializer(), str
            ).toModel()
        } catch (err: Throwable) {
            null
        }
    })

object Embedded {
    @Serializable
    data class VirtualTour(
        val href: String? = null,
    )

    @Serializable
    enum class Product {
        @SerialName("core.agent")
        CoreAgent,

        @SerialName("core.broker")
        CoreBroker,

        @SerialName("co_broke")
        CoBroke,
    }

    @Serializable
    enum class Type {
        @SerialName("condo")
        Condo,

        @SerialName("single_family")
        SingleFamily,

        @SerialName("land")
        Land,

        @SerialName("multi_family")
        MultiFamily,

        @SerialName("apartment")
        Apartment,
    }

    @Serializable
    enum class SubType {
        @SerialName("coop")
        Coop,

        @SerialName("condos")
        Condos,
    }

    @Serializable
    enum class Status {
        @SerialName("for_sale")
        ForSale,

        @SerialName("for_rent")
        ForRent,

        @SerialName("not_for_sale")
        NotForSale,
    }

    @Serializable
    data class Address(
        val city: String? = null,
        val line: String? = null,
        val postalCode: String? = null,
        val stateCode: String? = null,
        val state: String? = null,
        val county: String? = null,
        val fipsCode: String? = null,
        val countyNeededForUniq: Boolean? = null,
        val lat: Double? = null,
        val lon: Double? = null,
        val neighborhoodName: String? = null,
        val timeZone: String? = null
    )

    @Serializable
    data class Branding(
        val name: String? = null,   // property.branding.listing_office.lis_item.name
        val photo: String? = null,   // property.branding.listing_office.lis_item.photo
        val phone: String? = null,   // property.branding.listing_office.lis_item.phone
        val slogan: String? = null,   // property.branding.listing_office.lis_item.slogan
        val showRealtorLogo: Boolean? = null,   // property.branding.listing_office.lis_item.show_realtor_logo
        val link: String? = null,   // property.branding.listing_office.lis_item.link
        val accentColor: String? = null,   // property.branding.listing_office.lis_item.accentColor
    )

    @Serializable
    data class BuildingSize(
        val size: Long? = null, val units: String? = null
    )

    @Serializable
    data class OpenHouse(
        val startDate: String? = null,  // ISODate (i.e. "2022-09-11T18:00:00.000Z")
        val endDate: String? = null,    // ISODate (i.e. "2022-09-11T18:00:00.000Z")
        val timeZone: String? = null,   // i.e. "EST"
        val dst: Boolean? = null
    )

    @Serializable
    data class Agent(
        val id: String? = null,
        val primary: Boolean? = null,
        val advertiserID: String? = null,
        val photo: Photo? = null,
        val name: String? = null
    ) {
        @Serializable
        data class Photo(
            val href: String? = null,
        )
    }

    @Serializable
    data class Office(
        val id: String,
        val name: String,
    )

    @Serializable
    data class ClientDisplayFlags(
        val presentationStatus: PresentationStatus? = null,
        val isShowcase: Boolean? = null,
        val leadFormPhoneRequired: Boolean? = null,
        val priceChange: Long? = null,
        val isCoBrokeEmail: Boolean? = null,
        val hasOpenHouse: Boolean? = null,
        val isCoBrokePhone: Boolean? = null,
        val isNewListing: Boolean? = null,
        val isNewPlan: Boolean? = null,
        val isTurbo: Boolean? = null,
        val isOfficeStandardListing: Boolean? = null,
        val suppressMapPin: Boolean? = null,
        val isPending: Boolean? = null,
        val showContactALenderInLeadForm: Boolean? = null,
        val showVeteransUnitedInLeadForm: Boolean? = null,
        val flipTheMarketEnabled: Boolean? = null,
        val isShowcaseChoiceEnabled: Boolean? = null,
        val hasMatterport: Boolean? = null
    ) {
        @Serializable
        enum class PresentationStatus {
            @SerialName("for_sale")
            ForSale,

            @SerialName("for_rent")
            ForRent,

            @SerialName("recently_sold")
            RecentlySold,
        }
    }

    @Serializable
    data class LeadForms(
        val form: Fields? = null,
        val showAgent: Boolean? = null,
        val showBroker: Boolean? = null,
        val showBuilder: Boolean? = null,
        val showContactALender: Boolean? = null,
        val showVeteransUnited: Boolean? = null,
        val formType: FormType? = null,
        val leadType: LeadType? = null,
        val isLcmEnabled: Boolean? = null,
        val flipTheMarketEnabled: Boolean? = null,
        val localPhone: String? = null,
        val localPhones: LocalPhones? = null,
        val showTextLeads: Boolean? = null,
        val cashbackEnabled: Boolean? = null,
        val smarthomeEnabled: Boolean? = null
    ) {

        @Serializable
        data class Fields(
            val name: Validation? = null,
            val email: Validation? = null,
            val phone: Validation? = null,
            val message: Validation? = null,
            val show: Boolean? = null,
        ) {

            @Serializable
            data class Validation(
                val required: Boolean? = null,
                val minimumCharacterCount: Int? = null,   // "minimum_character_count"
                val maximumCharacterCount: Int? = null,
            )
        }

        @Serializable
        enum class FormType {
            @SerialName("classic")
            Classic // "classic"
        }

        @Serializable
        enum class LeadType {
            @SerialName("co_broke")
            CoBroke // co_broke
        }

        @Serializable
        data class LocalPhones(
            val commConsoleMweb: String? = null,  // comm_console_mweb (i.e. "(888)860-0947")
        )

    }

    @Serializable
    data class MultipleListingService(
        val id: String? = null,
        val name: String? = null,
        val planID: String? = null,
        val abbreviation: String? = null,
        val type: Type? = null,
    ) {
        @Serializable
        enum class Type {
            @SerialName("mls")
            Mls // "mls"
        }
    }

}

fun Embedded.Product.toModel(): Property.Product = when (this) {
    Embedded.Product.CoreAgent -> Property.Product.CoreAgent
    Embedded.Product.CoBroke -> Property.Product.CoBroke
    Embedded.Product.CoreBroker -> Property.Product.CoreBroker
}

fun Property.Product.toEmbedded(): Embedded.Product = when (this) {
    Property.Product.CoreAgent -> Embedded.Product.CoreAgent
    Property.Product.CoBroke -> Embedded.Product.CoBroke
    Property.Product.CoreBroker -> Embedded.Product.CoreBroker
}

fun Embedded.Type.toModel(): Property.Type = when (this) {
    Embedded.Type.Condo -> Property.Type.Condo
    Embedded.Type.Land -> Property.Type.Land
    Embedded.Type.SingleFamily -> Property.Type.SingleFamily
    Embedded.Type.Apartment -> Property.Type.Apartment
    Embedded.Type.MultiFamily -> Property.Type.MultiFamily
}

fun Property.Type.toEmbedded(): Embedded.Type = when (this) {
    Property.Type.Condo -> Embedded.Type.Condo
    Property.Type.Land -> Embedded.Type.Land
    Property.Type.SingleFamily -> Embedded.Type.SingleFamily
    Property.Type.Apartment -> Embedded.Type.Apartment
    Property.Type.MultiFamily -> Embedded.Type.MultiFamily
}

fun Embedded.SubType.toModel(): Property.SubType = when (this) {
    Embedded.SubType.Coop -> Property.SubType.Coop
    Embedded.SubType.Condos -> Property.SubType.Condos
}

fun Property.SubType.toEmbedded(): Embedded.SubType = when (this) {
    Property.SubType.Coop -> Embedded.SubType.Coop
    Property.SubType.Condos -> Embedded.SubType.Condos
}

fun Embedded.Status.toModel(): Property.Status = when (this) {
    Embedded.Status.ForSale -> Property.Status.ForSale
    Embedded.Status.ForRent -> Property.Status.ForRent
    Embedded.Status.NotForSale -> Property.Status.NotForSale
}

fun Property.Status.toEmbedded(): Embedded.Status = when (this) {
    Property.Status.ForSale -> Embedded.Status.ForSale
    Property.Status.ForRent -> Embedded.Status.ForRent
    Property.Status.NotForSale -> Embedded.Status.NotForSale
}


fun Property.toEntity(json: Json): PropertyEntity = PropertyEntity(
    propertyID = this.propertyID!!,
    listingID = this.listingID,
    products = json.encodeToString(
        ListSerializer(
            Embedded.Product.serializer()
        ), this.products.map(Property.Product::toEmbedded)
    ),
    rdcWebURL = this.rdcWebURL,
    propType = this.propType?.let {
        json.encodeToString(
            Embedded.Type.serializer(),
            it.toEmbedded(),
        )
    },
    propSubType = this.propSubType?.let {
        json.encodeToString(
            Embedded.SubType.serializer(),
            it.toEmbedded(),
        )
    },
    virtualTour = this.virtualTour?.let {
        json.encodeToString(
            Embedded.VirtualTour.serializer(), it.toEmbedded()
        )
    },
    address = this.address?.let {
        json.encodeToString(
            Embedded.Address.serializer(), it.toEmbedded()
        )
    },
    branding = this.branding?.let {
        json.encodeToString(
            Embedded.Branding.serializer(), it.toEmbedded()
        )
    },
    propStatus = this.propStatus?.let {
        json.encodeToString(
            Embedded.Status.serializer(),
            it.toEmbedded(),
        )
    },
    price = this.price,
    bathsFull = this.bathsFull?.toLong(),
    baths = this.baths?.toLong(),
    beds = this.beds?.toLong(),
    buildingSize = this.buildingSize?.let {
        json.encodeToString(
            Embedded.BuildingSize.serializer(), it.toEmbedded()
        )
    },
    openHouses = json.encodeToString(
        ListSerializer(Embedded.OpenHouse.serializer()),
        this.openHouses.map(OpenHouse::toEmbedded),
    ),
    agents = json.encodeToString(
        ListSerializer(Embedded.Agent.serializer()),
        this.agents.map(Agent::toEmbedded),
    ),
    office = this.office?.let {
        json.encodeToString(
            Embedded.Office.serializer(),
            it.toEmbedded(),
        )
    },
    lastUpdate = this.lastUpdate,
    clientDisplayFlags = this.clientDisplayFlags?.let {
        json.encodeToString(
            Embedded.ClientDisplayFlags.serializer(),
            it.toEmbedded(),
        )
    },
    leadForms = this.leadForms?.let {
        json.encodeToString(
            Embedded.LeadForms.serializer(),
            it.toEmbedded(),
        )
    },
    photoCount = this.photoCount?.toLong(),
    thumbnail = this.thumbnail,
    pageNo = this.pageNo?.toLong(),
    rank = this.rank,
    listTracking = this.listTracking,
    mls = this.mls?.let {
        json.encodeToString(
            Embedded.MultipleListingService.serializer(),
            it.toEmbedded(),
        )
    },
    dataSourceName = this.dataSourceName?.let {
        json.encodeToString(
            Embedded.MultipleListingService.Type.serializer(),
            it.toEmbedded(),
        )
    },
)
