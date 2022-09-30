package devlcc.io.kmmshowcaserealestate.core.model.property

import devlcc.io.kmmshowcaserealestate.core.model.CommonParcelable
import devlcc.io.kmmshowcaserealestate.core.model.CommonParcelize

@CommonParcelize
data class OpenHouse(
    val startDate: String? = null,  // ISODate (i.e. "2022-09-11T18:00:00.000Z")
    val endDate: String? = null,    // ISODate (i.e. "2022-09-11T18:00:00.000Z")
    val timeZone: String? = null,   // i.e. "EST"
    val dst: Boolean? = null
) : CommonParcelable
