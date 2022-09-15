package devlcc.io.kmmshowcaserealestate.core.network

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform