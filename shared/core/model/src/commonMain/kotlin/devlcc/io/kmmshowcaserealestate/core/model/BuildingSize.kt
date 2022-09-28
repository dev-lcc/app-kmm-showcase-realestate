package devlcc.io.kmmshowcaserealestate.core.model

@CommonParcelize
data class BuildingSize(
    val size: Long, val units: String
) : CommonParcelable
