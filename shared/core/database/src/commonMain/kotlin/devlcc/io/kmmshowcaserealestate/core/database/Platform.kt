package devlcc.io.kmmshowcaserealestate.core.database

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform