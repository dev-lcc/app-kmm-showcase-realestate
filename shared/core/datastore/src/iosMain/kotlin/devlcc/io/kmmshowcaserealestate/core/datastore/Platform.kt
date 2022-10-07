package devlcc.io.kmmshowcaserealestate.core.datastore

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

actual val platformDatastoreModule: Module = module {

//    single<NSUserDefaults> {
//        provideNSUserDefaults()
//    }

//    single<Settings> {
//        NSUserDefaultsSettings(
//            delegate = get(),
//            useFrozenListeners = false,
//        )
//    }
}

// internal fun provideNSUserDefaults() = NSUserDefaults.standardUserDefaults//(suiteName = SETTINGS_NAME)

/**
 * Helper class to resolve Core Datastore Module Dependencies
 */
//@Suppress("unused")
//object CoreDatastoreResolver: KoinComponent {
//    fun getAppDatastore() = get<AppDatastore>()
//}