package devlcc.io.kmmshowcaserealestate.core.model

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform