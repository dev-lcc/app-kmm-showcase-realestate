package devlcc.io.kmmshowcaserealestate.core.model.property

import devlcc.io.kmmshowcaserealestate.core.model.di.CommonParcelable
import devlcc.io.kmmshowcaserealestate.core.model.di.CommonParcelize

@CommonParcelize
data class Agent(
    val id: String? = null,
    val primary: Boolean? = null,
    val advertiserID: String? = null,
    val photo: Photo? = null,
    val name: String? = null
) : CommonParcelable {

    @CommonParcelize
    data class Photo(
        val href: String? = null,
    ): CommonParcelable

}
