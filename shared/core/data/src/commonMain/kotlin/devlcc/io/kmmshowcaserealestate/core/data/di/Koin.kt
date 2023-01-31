package devlcc.io.kmmshowcaserealestate.core.data.di

import co.touchlab.kermit.Logger
import devlcc.io.kmmshowcaserealestate.core.data.repository.FavoritesRepository
import devlcc.io.kmmshowcaserealestate.core.data.repository.PropertiesRepository
import devlcc.io.kmmshowcaserealestate.core.data.repository.impl.FavoritesRepositoryImpl
import devlcc.io.kmmshowcaserealestate.core.data.repository.impl.PropertiesRepositoryImpl
import devlcc.io.kmmshowcaserealestate.core.database.di.getCoreDatabaseModule
import devlcc.io.kmmshowcaserealestate.core.datastore.di.getCoreDatastoreModule
import devlcc.io.kmmshowcaserealestate.core.network.di.getCoreNetworkModule
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

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
//    newSingleThreadContext("KMMRealEstateShowcase-CoroutineDispatcher")
    newFixedThreadPoolContext(20, "KMMRealEstateShowcase-IOCoroutineDispatcher")

internal fun provideLogger(): Logger = Logger.withTag("KMMRealEstateShowcase")

internal fun provideJson(): Json = Json {
    prettyPrint = true
    isLenient = true
    ignoreUnknownKeys = true
}

internal expect val workerBgTaskManagerModule: Module