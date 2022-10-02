@file:JvmName("PlatformKmmCoreDatastore")

package devlcc.io.kmmshowcaserealestate.core.datastore

import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.jvm.JvmName

val coreDatastoreModule: Module = module {
    single<AppDatastore> {
        AppDatastoreImpl(
            settings = get()
        )
    }
}.apply { includes(platformDatastoreModule) }

internal const val SETTINGS_NAME = "KMMRealEstateShowcase"

expect val platformDatastoreModule: Module