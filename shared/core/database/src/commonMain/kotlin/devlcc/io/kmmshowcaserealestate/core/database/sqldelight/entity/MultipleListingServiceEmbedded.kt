package devlcc.io.kmmshowcaserealestate.core.database.sqldelight.entity

import devlcc.io.kmmshowcaserealestate.core.model.property.MultipleListingService

fun Embedded.MultipleListingService.toModel(): MultipleListingService = MultipleListingService(
    id = this.id,
    name = this.name,
    planID = this.planID,
    abbreviation = this.abbreviation,
    type = when (this.type) {
        Embedded.MultipleListingService.Type.Mls -> MultipleListingService.Type.Mls
        else -> null
    }
)

fun MultipleListingService.toEmbedded(): Embedded.MultipleListingService =
    Embedded.MultipleListingService(
        id = this.id,
        name = this.name,
        planID = this.planID,
        abbreviation = this.abbreviation,
        type = when (this.type) {
            MultipleListingService.Type.Mls -> Embedded.MultipleListingService.Type.Mls
            else -> null
        }
    )

fun Embedded.MultipleListingService.Type.toModel(): MultipleListingService.Type =
    when(this) {
        Embedded.MultipleListingService.Type.Mls -> MultipleListingService.Type.Mls
    }

fun MultipleListingService.Type.toEmbedded(): Embedded.MultipleListingService.Type =
    when(this) {
        MultipleListingService.Type.Mls -> Embedded.MultipleListingService.Type.Mls
    }

