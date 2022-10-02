@file:JvmName("PlatformJvmCoreDatastore")

package devlcc.io.kmmshowcaserealestate.core.datastore

import android.content.Context
import android.content.SharedPreferences
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.core.module.Module
import org.koin.dsl.module


actual val platformDatastoreModule: Module = module {
    single<SharedPreferences> {
        providesSharedPreferences(context = get())
    }

    single<Settings> {
        SharedPreferencesSettings(
            delegate = get(), commit = true
        )
    }
}

internal fun providesSharedPreferences(
    context: Context
) = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE)
