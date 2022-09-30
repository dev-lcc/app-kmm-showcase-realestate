package devlcc.io.kmmshowcaserealestate.core.model.property

import devlcc.io.kmmshowcaserealestate.core.model.CommonParcelable
import devlcc.io.kmmshowcaserealestate.core.model.CommonParcelize

@CommonParcelize
data class Office(
    val id: String,
    val name: String,
) : CommonParcelable
