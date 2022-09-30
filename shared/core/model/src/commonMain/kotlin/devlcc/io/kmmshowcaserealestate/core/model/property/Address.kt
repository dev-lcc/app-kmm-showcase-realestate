package devlcc.io.kmmshowcaserealestate.core.model.property

import devlcc.io.kmmshowcaserealestate.core.model.CommonParcelable
import devlcc.io.kmmshowcaserealestate.core.model.CommonParcelize

@CommonParcelize
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
) : CommonParcelable
