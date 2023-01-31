package devlcc.io.kmmshowcaserealestate.core.model.property

import devlcc.io.kmmshowcaserealestate.core.model.di.CommonParcelable
import devlcc.io.kmmshowcaserealestate.core.model.di.CommonParcelize

@CommonParcelize
data class BuildingSize(
    val size: Long? = null,
    val units: String? = null
) : CommonParcelable
