package devlcc.io.kmmshowcaserealestate.core.network.ktor.dto

import devlcc.io.kmmshowcaserealestate.core.model.property.Agent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AgentDTO(
    val primary: Boolean? = null,

    @SerialName("advertiser_id")
    val advertiserID: String? = null,

    val id: String? = null,
    val photo: Photo? = null,
    val name: String? = null,
) {
    @Serializable
    data class Photo(
        val href: String? = null,
    )
}

fun AgentDTO.toModel(): Agent =
    Agent(
        id = this.id,
        photo = this.photo?.let { p -> Agent.Photo(href = p.href) },
        name = this.name,
        primary = this.primary,
        advertiserID = this.advertiserID,
    )