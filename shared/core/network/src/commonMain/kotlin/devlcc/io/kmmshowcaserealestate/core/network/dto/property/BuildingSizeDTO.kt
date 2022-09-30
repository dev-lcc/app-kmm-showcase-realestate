package devlcc.io.kmmshowcaserealestate.core.network.dto.property

import devlcc.io.kmmshowcaserealestate.core.model.property.BuildingSize
import kotlinx.serialization.Serializable

@Serializable
data class BuildingSizeDTO(
    val size: Long? = null,
    val units: String? = null,
)

fun BuildingSizeDTO.toModel(): BuildingSize =
    BuildingSize(
        size = this.size,
        units = this.units,
    )