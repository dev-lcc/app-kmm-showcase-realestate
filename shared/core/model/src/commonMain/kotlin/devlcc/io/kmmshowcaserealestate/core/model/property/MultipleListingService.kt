package devlcc.io.kmmshowcaserealestate.core.model.property

import devlcc.io.kmmshowcaserealestate.core.model.CommonParcelable
import devlcc.io.kmmshowcaserealestate.core.model.CommonParcelize

/**
 * MLS
 */
@CommonParcelize
data class MultipleListingService(
    val id: String? = null,
    val name: String? = null,
    val planID: String? = null,
    val abbreviation: String? = null,
    val type: Type? = null,
) : CommonParcelable {

    @CommonParcelize
    enum class Type : CommonParcelable {
        Mls // "mls"
    }

}
