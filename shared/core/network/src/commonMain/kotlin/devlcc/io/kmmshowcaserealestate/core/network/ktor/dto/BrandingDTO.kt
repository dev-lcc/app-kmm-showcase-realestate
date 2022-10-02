package devlcc.io.kmmshowcaserealestate.core.network.ktor.dto

import devlcc.io.kmmshowcaserealestate.core.model.property.Branding
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class BrandingDTO(
    @SerialName("listing_office")
    val listingOffice: ListingOfficeDTO? = null,
)

@Serializable
data class ListingOfficeDTO (
    @SerialName("list_item")
    val listItem: ListItemDTO? = null,
)

@Serializable
data class ListItemDTO (
    val name: String? = null,
    val photo: String? = null,
    val phone: String? = null,
    val slogan: String? = null,

    @SerialName("show_realtor_logo")
    val showRealtorLogo: Boolean? = null,

    val link: String? = null,

    @SerialName("accent_color")
    val accentColor: String? = null
)

fun BrandingDTO.toModel(): Branding =
    Branding(
        name = this.listingOffice?.listItem?.name,
        photo = this.listingOffice?.listItem?.photo,
        phone = this.listingOffice?.listItem?.phone,
        slogan = this.listingOffice?.listItem?.slogan,
        showRealtorLogo = this.listingOffice?.listItem?.showRealtorLogo,
        link = this.listingOffice?.listItem?.link,
        accentColor = this.listingOffice?.listItem?.accentColor,
    )