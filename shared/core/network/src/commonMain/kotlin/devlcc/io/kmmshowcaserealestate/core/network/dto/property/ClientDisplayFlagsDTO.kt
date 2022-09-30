package devlcc.io.kmmshowcaserealestate.core.network.dto.property

import devlcc.io.kmmshowcaserealestate.core.model.property.ClientDisplayFlags
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ClientDisplayFlagsDTO (
    @SerialName("presentation_status")
    val presentationStatus: String? = null,

    @SerialName("is_showcase")
    val isShowcase: Boolean? = null,

    @SerialName("lead_form_phone_required")
    val leadFormPhoneRequired: Boolean? = null,

    @SerialName("price_change")
    val priceChange: Long? = null,

    @SerialName("is_co_broke_email")
    val isCoBrokeEmail: Boolean? = null,

    @SerialName("has_open_house")
    val hasOpenHouse: Boolean? = null,

    @SerialName("is_co_broke_phone")
    val isCoBrokePhone: Boolean? = null,

    @SerialName("is_new_listing")
    val isNewListing: Boolean? = null,

    @SerialName("is_new_plan")
    val isNewPlan: Boolean? = null,

    @SerialName("is_turbo")
    val isTurbo: Boolean? = null,

    @SerialName("is_office_standard_listing")
    val isOfficeStandardListing: Boolean? = null,

    @SerialName("suppress_map_pin")
    val suppressMapPin: Boolean? = null,

    @SerialName("is_pending")
    val isPending: Boolean? = null,

    @SerialName("show_contact_a_lender_in_lead_form")
    val showContactALenderInLeadForm: Boolean? = null,

    @SerialName("show_veterans_united_in_lead_form")
    val showVeteransUnitedInLeadForm: Boolean? = null,

    @SerialName("flip_the_market_enabled")
    val flipTheMarketEnabled: Boolean? = null,

    @SerialName("is_showcase_choice_enabled")
    val isShowcaseChoiceEnabled: Boolean? = null,

    @SerialName("has_matterport")
    val hasMatterport: Boolean? = null,
)

fun ClientDisplayFlagsDTO.toModel(): ClientDisplayFlags =
    ClientDisplayFlags(
        presentationStatus = this.presentationStatus?.let { str ->
            when(str) {
                "for_sale" -> ClientDisplayFlags.PresentationStatus.ForSale
                "for_rent" -> ClientDisplayFlags.PresentationStatus.ForRent
                "recently_sold" -> ClientDisplayFlags.PresentationStatus.RecentlySold
                else -> null
            }
        },
        isShowcase = this.isShowcase,
        leadFormPhoneRequired = this.leadFormPhoneRequired,
        priceChange = this.priceChange,
        isCoBrokeEmail = this.isCoBrokeEmail,
        hasOpenHouse = this.hasOpenHouse,
        isCoBrokePhone = this.isCoBrokePhone,
        isNewListing = this.isNewListing,
        isNewPlan = this.isNewPlan,
        isTurbo = this.isTurbo,
        isOfficeStandardListing = this.isOfficeStandardListing,
        suppressMapPin = this.suppressMapPin,
        isPending = this.isPending,
        showContactALenderInLeadForm = this.showContactALenderInLeadForm,
        showVeteransUnitedInLeadForm = this.showVeteransUnitedInLeadForm,
        flipTheMarketEnabled = this.flipTheMarketEnabled,
        isShowcaseChoiceEnabled = this.isShowcaseChoiceEnabled,
        hasMatterport = this.hasMatterport,
    )