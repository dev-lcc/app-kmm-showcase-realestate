package devlcc.io.kmmshowcaserealestate.core.database.sqldelight.entity

import devlcc.io.kmmshowcaserealestate.core.model.property.ClientDisplayFlags

fun Embedded.ClientDisplayFlags.toModel(): ClientDisplayFlags = ClientDisplayFlags(
    presentationStatus = this.presentationStatus?.let { embedded ->
        when (embedded) {
            Embedded.ClientDisplayFlags.PresentationStatus.ForSale -> ClientDisplayFlags.PresentationStatus.ForSale
            Embedded.ClientDisplayFlags.PresentationStatus.ForRent -> ClientDisplayFlags.PresentationStatus.ForRent
            Embedded.ClientDisplayFlags.PresentationStatus.RecentlySold -> ClientDisplayFlags.PresentationStatus.RecentlySold
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

fun ClientDisplayFlags.toEmbedded(): Embedded.ClientDisplayFlags =
    Embedded.ClientDisplayFlags(
        presentationStatus = this.presentationStatus?.let {
            when (it) {
                ClientDisplayFlags.PresentationStatus.ForSale -> Embedded.ClientDisplayFlags.PresentationStatus.ForSale
                ClientDisplayFlags.PresentationStatus.ForRent -> Embedded.ClientDisplayFlags.PresentationStatus.ForRent
                ClientDisplayFlags.PresentationStatus.RecentlySold -> Embedded.ClientDisplayFlags.PresentationStatus.RecentlySold
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
