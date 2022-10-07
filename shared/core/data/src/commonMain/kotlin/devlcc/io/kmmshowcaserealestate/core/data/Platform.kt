@file:JvmName("PlatformKmmCoreData")

package devlcc.io.kmmshowcaserealestate.core.data

import co.touchlab.kermit.Logger
import devlcc.io.kmmshowcaserealestate.core.data.repository.FavoritesRepository
import devlcc.io.kmmshowcaserealestate.core.data.repository.PropertiesRepository
import devlcc.io.kmmshowcaserealestate.core.data.repository.impl.FavoritesRepositoryImpl
import devlcc.io.kmmshowcaserealestate.core.data.repository.impl.PropertiesRepositoryImpl
import devlcc.io.kmmshowcaserealestate.core.database.getCoreDatabaseModule
import devlcc.io.kmmshowcaserealestate.core.datastore.getCoreDatastoreModule
import devlcc.io.kmmshowcaserealestate.core.network.getCoreNetworkModule
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.jvm.JvmName

internal fun initKoin(
    isDebug: Boolean,
    appModule: Module/* = module {}*/,
): KoinApplication {
    val koinApplication = startKoin {
        modules(
            appModule,
            getCoreDataModule(isDebug)
        )
    }

    return koinApplication
}

fun getCoreDataModule(
    isDebug: Boolean
) = module {

    single<Logger> { provideLogger() }
    single<Json> { provideJson() }
    single<CoroutineDispatcher> { provideIOCoroutineDispatcher() }

    includes(
        getCoreNetworkModule(isDebug = isDebug),
        getCoreDatabaseModule(),
        getCoreDatastoreModule(),
    )

    single<PropertiesRepository> {
        PropertiesRepositoryImpl(
            propertiesApiService = get(),
            propertiesLocalDao = get(),
        )
    }

    single<FavoritesRepository> {
        FavoritesRepositoryImpl(
            favoriteDao = get(),
        )
    }

    // TODO:: Put Repository instances here...

    // Platform-specific `WorkerBGTaskManager` instance module
    includes(workerBgTaskManagerModule)

}

fun provideIOCoroutineDispatcher(): CoroutineDispatcher =
    newFixedThreadPoolContext(20, "KMMRealEstateShowcase-CoroutineDispatcher")

internal fun provideLogger(): Logger = Logger.withTag("KMMRealEstateShowcase")

internal fun provideJson(): Json = Json {
    prettyPrint = true
    isLenient = true
    ignoreUnknownKeys = true
}

internal expect val workerBgTaskManagerModule: Module