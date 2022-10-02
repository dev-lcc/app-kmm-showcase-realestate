package devlcc.io.kmmshowcaserealestate.core.network.ktor.dto

import devlcc.io.kmmshowcaserealestate.core.model.property.Office
import kotlinx.serialization.Serializable

@Serializable
data class OfficeDTO(
    val id: String,
    val name: String,
)

fun OfficeDTO.toModel(): Office =
    Office(
        id = this.id,
        name = this.name,
    )
