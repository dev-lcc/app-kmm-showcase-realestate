package devlcc.io.kmmshowcaserealestate.core.database.sqldelight.entity

import devlcc.io.kmmshowcaserealestate.core.model.property.BuildingSize

fun Embedded.BuildingSize.toModel(): BuildingSize = BuildingSize(
    size = this.size,
    units = this.units,
)

fun BuildingSize.toEmbedded(): Embedded.BuildingSize =
    Embedded.BuildingSize(
        size = this.size,
        units = this.units,
    )