package devlcc.io.kmmshowcaserealestate.core.model.property

import devlcc.io.kmmshowcaserealestate.core.model.CommonParcelable
import devlcc.io.kmmshowcaserealestate.core.model.CommonParcelize

@CommonParcelize
data class Branding(
    val name: String? = null,   // property.branding.listing_office.lis_item.name
    val photo: String? = null,   // property.branding.listing_office.lis_item.photo
    val phone: String? = null,   // property.branding.listing_office.lis_item.phone
    val slogan: String? = null,   // property.branding.listing_office.lis_item.slogan
    val showRealtorLogo: Boolean? = null,   // property.branding.listing_office.lis_item.show_realtor_logo
    val link: String? = null,   // property.branding.listing_office.lis_item.link
    val accentColor: String? = null,   // property.branding.listing_office.lis_item.accentColor

) : CommonParcelable
