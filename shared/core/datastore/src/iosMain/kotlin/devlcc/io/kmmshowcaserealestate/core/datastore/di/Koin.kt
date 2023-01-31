package devlcc.io.kmmshowcaserealestate.core.datastore.di

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import devlcc.io.kmmshowcaserealestate.core.datastore.AppDatastore
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

actual val platformDatastoreModule: Module = module {

    single<Settings> {
        NSUserDefaultsSettings(
            delegate = NSUserDefaults(suiteName = SETTINGS_NAME)
        )
    }
}

/**
 * Helper class to resolve Core Datastore Module Dependencies
 */
@Suppress("unused")
object CoreDatastoreResolver : KoinComponent {
    fun getAppDatastore() = get<AppDatastore>()
}