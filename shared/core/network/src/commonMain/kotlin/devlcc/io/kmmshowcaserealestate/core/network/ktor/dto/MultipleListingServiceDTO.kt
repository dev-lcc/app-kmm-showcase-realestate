package devlcc.io.kmmshowcaserealestate.core.network.ktor.dto

import devlcc.io.kmmshowcaserealestate.core.model.property.MultipleListingService
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class MultipleListingServiceDTO(
    val name: String? = null,
    val id: String? = null,

    @SerialName("plan_id")
    val planID: String? = null,

    val abbreviation: String? = null,
    val type: String? = null,
)

fun MultipleListingServiceDTO.toModel(): MultipleListingService =
    MultipleListingService(
        name = this.name,
        id = this.id,
        planID = this.planID,
        abbreviation = this.abbreviation,
        type = this.type?.let { str ->
            when(str) {
                "mls" -> MultipleListingService.Type.Mls
                else -> null
            }
        },
    )
