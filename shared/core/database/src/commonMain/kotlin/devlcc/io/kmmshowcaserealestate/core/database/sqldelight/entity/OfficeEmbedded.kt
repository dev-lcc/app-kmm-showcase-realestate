package devlcc.io.kmmshowcaserealestate.core.database.sqldelight.entity

import devlcc.io.kmmshowcaserealestate.core.model.property.Office

fun Embedded.Office.toModel(): Office = Office(
    id = this.id,
    name = this.name,
)

fun Office.toEmbedded(): Embedded.Office =
    Embedded.Office(
        id = this.id,
        name = this.name,
    )