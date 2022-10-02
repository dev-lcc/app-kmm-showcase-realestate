package devlcc.io.kmmshowcaserealestate.core.network.ktor.dto

import devlcc.io.kmmshowcaserealestate.core.model.property.MultipleListingService
import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PropertyDTO(
    @SerialName("property_id")
    val propertyID: String? = null,

    @SerialName("listing_id")
    val listingID: String? = null,

    val products: List<String> = emptyList(),

    @SerialName("rdc_web_url")
    val rdcWebURL: String? = null,

    @SerialName("prop_type")
    val propType: String? = null,

    val address: AddressDTO? = null,
    val branding: BrandingDTO? = null,

    @SerialName("prop_status")
    val propStatus: String? = null,

    val price: Long? = null,

    @SerialName("baths_full")
    val bathsFull: Int? = null,

    val baths: Int? = null,
    val beds: Int? = null,
    val agents: List<AgentDTO> = emptyList(),
    val office: OfficeDTO? = null,

    @SerialName("last_update")
    val lastUpdate: String? = null,

    @SerialName("client_display_flags")
    val clientDisplayFlags: ClientDisplayFlagsDTO? = null,

    @SerialName("lead_forms")
    val leadForms: LeadFormsDTO? = null,

    @SerialName("photo_count")
    val photoCount: Int? = null,

    val thumbnail: String? = null,

    @SerialName("page_no")
    val pageNo: Int? = null,

    val rank: Long? = null,

    @SerialName("list_tracking")
    val listTracking: String? = null,

    val mls: MultipleListingServiceDTO? = null,

    @SerialName("data_source_name")
    val dataSourceName: String? = null,

    @SerialName("virtual_tour")
    val virtualTour: VirtualTour? = null,

    @SerialName("building_size")
    val buildingSize: BuildingSizeDTO? = null,

    @SerialName("open_houses")
    val openHouses: List<OpenHouseDTO> = emptyList(),

    @SerialName("prop_sub_type")
    val propSubType: String? = null
) {

    @Serializable
    data class VirtualTour(
        val href: String? = null,
    )
}

fun PropertyDTO.toModel(): Property =
    Property(
        propertyID = this.propertyID,
        listingID = this.listingID,
        products = this.products
            .mapNotNull { str ->
                when(str) {
                    "core.agent" -> Property.Product.CoreAgent
                    "core.broker" -> Property.Product.CoreBroker
                    "co_broke" -> Property.Product.CoBroke
                    else -> null
                }
            },
        rdcWebURL = this.rdcWebURL,
        propType = this.propType?.let { str ->
            when(str) {
                "condo" -> Property.Type.Condo
                "single_family" -> Property.Type.SingleFamily
                "land" -> Property.Type.Land
                "multi_family" -> Property.Type.MultiFamily
                "apartment" -> Property.Type.Apartment
                else -> null
            }
        },
        address = this.address?.toModel(),
        branding = this.branding?.toModel(),
        propStatus = this.propStatus?.let { str ->
            when(str) {
                "for_sale" -> Property.Status.ForSale
                "for_rent" -> Property.Status.ForRent
                "not_for_sale" -> Property.Status.NotForSale
                else -> null
            }
        },
        price = this.price,
        bathsFull = this.bathsFull,
        baths = this.baths,
        beds = this.beds,
        agents = this.agents.map { it.toModel() },
        office = this.office?.toModel(),
        lastUpdate = this.lastUpdate,
        clientDisplayFlags = this.clientDisplayFlags?.toModel(),
        leadForms = this.leadForms?.toModel(),
        photoCount = this.photoCount,
        thumbnail = this.thumbnail,
        pageNo = this.pageNo,
        rank = this.rank,
        listTracking = this.listTracking,
        mls = this.mls?.toModel(),
        dataSourceName = this.dataSourceName?.let { str ->
            when(str) {
                "mls" -> MultipleListingService.Type.Mls
                else -> null
            }
        },
        virtualTour = this.virtualTour?.toModel(),
        buildingSize = this.buildingSize?.toModel(),
        openHouses = this.openHouses.map { it.toModel() },
        propSubType = this.propSubType?.let { str ->
            when(str) {
                "coop" -> Property.SubType.Coop
                "condos" -> Property.SubType.Condos
                else -> null
            }
        },
    )

fun PropertyDTO.VirtualTour.toModel(): Property.VirtualTour =
    Property.VirtualTour(
        href = this.href
    )
