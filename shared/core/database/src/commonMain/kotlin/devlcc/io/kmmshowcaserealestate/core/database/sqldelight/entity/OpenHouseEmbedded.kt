package devlcc.io.kmmshowcaserealestate.core.database.sqldelight.entity

import devlcc.io.kmmshowcaserealestate.core.model.property.OpenHouse

fun Embedded.OpenHouse.toModel(): OpenHouse = OpenHouse(
    startDate = this.startDate,
    endDate = this.endDate,
    timeZone = this.timeZone,
    dst = this.dst,
)

fun OpenHouse.toEmbedded(): Embedded.OpenHouse =
    Embedded.OpenHouse(
        startDate = this.startDate,
        endDate = this.endDate,
        timeZone = this.timeZone,
        dst = this.dst,
    )