package devlcc.io.kmmshowcaserealestate.core.data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform