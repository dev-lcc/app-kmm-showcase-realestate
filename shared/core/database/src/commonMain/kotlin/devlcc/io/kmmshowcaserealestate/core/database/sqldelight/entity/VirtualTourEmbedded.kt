package devlcc.io.kmmshowcaserealestate.core.database.sqldelight.entity

import devlcc.io.kmmshowcaserealestate.core.model.property.Property

fun Embedded.VirtualTour.toModel(): Property.VirtualTour = Property.VirtualTour(
    href = this.href
)

fun Property.VirtualTour.toEmbedded(): Embedded.VirtualTour =
    Embedded.VirtualTour(
        href = this.href
    )