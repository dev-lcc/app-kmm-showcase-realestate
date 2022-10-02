package devlcc.io.kmmshowcaserealestate.core.database.sqldelight.entity

import devlcc.io.kmmshowcaserealestate.core.model.property.Agent

fun Embedded.Agent.toModel(): Agent = Agent(
    id = this.id,
    primary = this.primary,
    advertiserID = this.advertiserID,
    photo = Agent.Photo(
        href = this.photo?.href,
    ),
    name = this.name,
)

fun Agent.toEmbedded(): Embedded.Agent =
    Embedded.Agent(
        id = this.id,
        primary = this.primary,
        advertiserID = this.advertiserID,
        photo = Embedded.Agent.Photo(
            href = this.photo?.href,
        ),
        name = this.name,
    )