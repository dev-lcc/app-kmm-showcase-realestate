package devlcc.io.kmmshowcaserealestate.core.network.dto.property

import devlcc.io.kmmshowcaserealestate.core.model.property.OpenHouse
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class OpenHouseDTO(
    @SerialName("start_date")
    val startDate: String? = null,

    @SerialName("end_date")
    val endDate: String? = null,

    @SerialName("time_zone")
    val timeZone: String? = null,

    val dst: Boolean? = null,
)

fun OpenHouseDTO.toModel(): OpenHouse =
    OpenHouse(
        startDate = this.startDate,
        endDate = this.endDate,
        timeZone = this.timeZone,
        dst = this.dst,
    )