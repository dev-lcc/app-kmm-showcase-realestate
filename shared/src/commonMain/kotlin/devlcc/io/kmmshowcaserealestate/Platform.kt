package devlcc.io.kmmshowcaserealestate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform