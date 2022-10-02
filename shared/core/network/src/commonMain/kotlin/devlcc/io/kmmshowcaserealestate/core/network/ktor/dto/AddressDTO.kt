package devlcc.io.kmmshowcaserealestate.core.network.ktor.dto

import devlcc.io.kmmshowcaserealestate.core.model.property.Address
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressDTO(
    val city: String? = null,
    val line: String? = null,

    @SerialName("postal_code")
    val postalCode: String? = null,

    @SerialName("state_code")
    val stateCode: String? = null,

    val state: String? = null,
    val county: String? = null,

    @SerialName("fips_code")
    val fipsCode: String? = null,

    @SerialName("county_needed_for_uniq")
    val countyNeededForUniq: Boolean? = null,

    val lat: Double? = null,
    val lon: Double? = null,

    @SerialName("neighborhood_name")
    val neighborhoodName: String? = null,

    @SerialName("time_zone")
    val timeZone: String? = null
)

fun AddressDTO.toModel(): Address = Address(
    city = this.city,
    line = this.line,
    postalCode = this.postalCode,
    stateCode = this.stateCode,
    state = this.state,
    county = this.county,
    fipsCode = this.fipsCode,
    countyNeededForUniq = this.countyNeededForUniq,
    lat = this.lat,
    lon = this.lon,
    neighborhoodName = this.neighborhoodName,
    timeZone = this.timeZone,
)
