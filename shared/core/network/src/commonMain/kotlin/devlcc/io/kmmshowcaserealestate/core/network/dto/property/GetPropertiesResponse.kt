package devlcc.io.kmmshowcaserealestate.core.network.dto.property

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GetPropertiesResponse (
    val meta: Meta? = null,
    val properties: List<PropertyDTO> = emptyList(),
)

@Serializable
data class Meta (
    val build: String? = null,
    val schema: String? = null,

    @SerialName("tracking_params")
    val trackingParams: TrackingParams? = null,

    val tracking: String? = null,

    @SerialName("returned_rows")
    val returnedRows: Int? = null,

    @SerialName("matching_rows")
    val matchingRows: Int? = null,
)

@Serializable
data class TrackingParams (
    val channel: String? = null,
    val siteSection: String? = null,
    val city: String? = null,
    val county: String? = null,
    val neighborhood: String? = null,
    val searchCityState: String? = null,
    val state: String? = null,
    val zip: String? = null,
    val srpPropertyStatus: String? = null,
    val listingActivity: String? = null,
    val propertyStatus: String? = null,
    val propertyType: String? = null,
    val searchBathrooms: String? = null,
    val searchBedrooms: String? = null,
    val searchMaxPrice: String? = null,
    val searchMinPrice: String? = null,
    val searchRadius: String? = null,
    val searchHouseSqft: String? = null,
    val searchLotSqft: String? = null,
    val searchResults: String? = null,
    val sortResults: String? = null,
    val searchCoordinates: String? = null,
    val version: String? = null,
)


