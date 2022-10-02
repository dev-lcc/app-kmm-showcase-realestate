package devlcc.io.kmmshowcaserealestate.core.database.sqldelight.entity

import devlcc.io.kmmshowcaserealestate.core.model.property.Branding

fun Embedded.Branding.toModel(): Branding = Branding(
    name = this.name,
    photo = this.photo,
    phone = this.phone,
    slogan = this.slogan,
    showRealtorLogo = this.showRealtorLogo,
    link = this.link,
    accentColor = this.accentColor,
)

fun Branding.toEmbedded(): Embedded.Branding =
    Embedded.Branding(
        name = this.name,
        photo = this.photo,
        phone = this.phone,
        slogan = this.slogan,
        showRealtorLogo = this.showRealtorLogo,
        link = this.link,
        accentColor = this.accentColor,
    )