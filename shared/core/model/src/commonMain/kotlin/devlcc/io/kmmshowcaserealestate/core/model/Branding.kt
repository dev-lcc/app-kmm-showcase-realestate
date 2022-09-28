package devlcc.io.kmmshowcaserealestate.core.model

@CommonParcelize
data class Branding(
    val name: String? = null,   // property.branding.listing_office.lis_item.name
    val photo: String? = null,   // property.branding.listing_office.lis_item.photo
    val phone: String? = null,   // property.branding.listing_office.lis_item.phone
    val slogan: String? = null,   // property.branding.listing_office.lis_item.slogan
    val showRealtorLogo: Boolean? = null,   // property.branding.listing_office.lis_item.show_realtor_logo
    val link: String? = null,   // property.branding.listing_office.lis_item.link
    val accentColor: String? = null,   // property.branding.listing_office.lis_item.accentColor
    val stateLicense: String? = null,   // property.branding.state_license
) : CommonParcelable
