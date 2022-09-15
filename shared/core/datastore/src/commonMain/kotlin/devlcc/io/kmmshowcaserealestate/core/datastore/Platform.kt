package devlcc.io.kmmshowcaserealestate.core.datastore

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform