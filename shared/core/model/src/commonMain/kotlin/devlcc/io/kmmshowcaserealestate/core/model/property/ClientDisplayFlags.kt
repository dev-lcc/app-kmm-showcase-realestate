package devlcc.io.kmmshowcaserealestate.core.model.property

import devlcc.io.kmmshowcaserealestate.core.model.CommonParcelable
import devlcc.io.kmmshowcaserealestate.core.model.CommonParcelize

@CommonParcelize
data class ClientDisplayFlags(
    val presentationStatus: PresentationStatus? = null,
    val isShowcase: Boolean? = null,
    val leadFormPhoneRequired: Boolean? = null,
    val priceChange: Long? = null,
    val isCoBrokeEmail: Boolean? = null,
    val hasOpenHouse: Boolean? = null,
    val isCoBrokePhone: Boolean? = null,
    val isNewListing: Boolean? = null,
    val isNewPlan: Boolean? = null,
    val isTurbo: Boolean? = null,
    val isOfficeStandardListing: Boolean? = null,
    val suppressMapPin: Boolean? = null,
    val isPending: Boolean? = null,
    val showContactALenderInLeadForm: Boolean? = null,
    val showVeteransUnitedInLeadForm: Boolean? = null,
    val flipTheMarketEnabled: Boolean? = null,
    val isShowcaseChoiceEnabled: Boolean? = null,
    val hasMatterport: Boolean? = null
) : CommonParcelable {

    @CommonParcelize
    enum class PresentationStatus: CommonParcelable {
        ForSale,    // "for_sale"
        ForRent,    // "for_rent"
        RecentlySold,   // "recently_sold"
    }

}
