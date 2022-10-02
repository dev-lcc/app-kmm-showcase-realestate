package devlcc.io.kmmshowcaserealestate.core.datastore

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

interface AppDatastore {

    var isDarkTheme: Boolean

    var onBoardingDone: Boolean

}

class AppDatastoreImpl(
    private val settings: Settings
) : AppDatastore {
    override var isDarkTheme: Boolean
        get() = settings.getBoolean(KEY_IS_DARK_THEME, false)
        set(value) {
            settings[KEY_IS_DARK_THEME] = value
        }
    override var onBoardingDone: Boolean
        get() = settings.getBoolean(KEY_ON_BOARD_DONE, false)
        set(value) {
            settings[KEY_ON_BOARD_DONE] = value
        }

    companion object {
        const val KEY_IS_DARK_THEME = "key-is-dark-theme"
        const val KEY_ON_BOARD_DONE = "key-on-board-done"
    }
}