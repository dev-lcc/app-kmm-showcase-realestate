package devlcc.io.kmmshowcaserealestate.core.model

/**
 * MLS
 */
@CommonParcelize
data class MultipleListingService(
    val id: String? = null,
    val name: String? = null,
    val planID: String? = null,
    val abbreviation: String? = null,
    val type: MultipleListingService.Type? = null,
) : CommonParcelable {

    @CommonParcelize
    enum class Type : CommonParcelable {
        Mls
    }

}
