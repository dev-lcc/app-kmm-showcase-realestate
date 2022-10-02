package devlcc.io.kmmshowcaserealestate.core.database.sqldelight.entity

import devlcc.io.kmmshowcaserealestate.core.model.property.Address

fun Embedded.Address.toModel(): Address = Address(
    city = this.city,
    line = this.line,
    postalCode = this.postalCode,
    stateCode = this.stateCode,
    state = this.state,
    county = this.county,
    fipsCode = this.fipsCode,
    countyNeededForUniq = this.countyNeededForUniq,
    lat = this.lat,
    lon = this.lon,
    neighborhoodName = this.neighborhoodName,
    timeZone = this.timeZone,
)

fun Address.toEmbedded(): Embedded.Address =
    Embedded.Address(
        city = this.city,
        line = this.line,
        postalCode = this.postalCode,
        stateCode = this.stateCode,
        state = this.state,
        county = this.county,
        fipsCode = this.fipsCode,
        countyNeededForUniq = this.countyNeededForUniq,
        lat = this.lat,
        lon = this.lon,
        neighborhoodName = this.neighborhoodName,
        timeZone = this.timeZone,
    )
