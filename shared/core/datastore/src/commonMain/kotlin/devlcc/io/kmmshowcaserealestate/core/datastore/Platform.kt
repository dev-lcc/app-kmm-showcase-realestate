@file:JvmName("PlatformKmmCoreDatastore")

package devlcc.io.kmmshowcaserealestate.core.datastore

import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.jvm.JvmName

fun getCoreDatastoreModule(): Module = module {
//    single<AppDatastore> {
//        AppDatastoreImpl(
//            settings = get()
//        )
//    }

    includes(platformDatastoreModule)
}

internal const val SETTINGS_NAME = "KMMRealEstateShowcase"

expect val platformDatastoreModule: Module