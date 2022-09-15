package devlcc.io.kmmshowcaserealestate.core.datastore

class Greeting {
    private val platform: Platform = getPlatform()

    fun greeting(): String {
        return "Hello, ${platform.name}!"
    }
}