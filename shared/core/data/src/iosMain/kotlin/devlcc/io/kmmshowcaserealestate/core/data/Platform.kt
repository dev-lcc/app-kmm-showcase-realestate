package devlcc.io.kmmshowcaserealestate.core.data

import co.touchlab.kermit.Logger
import devlcc.io.kmmshowcaserealestate.core.data.managers.WorkerBGTaskManager
import devlcc.io.kmmshowcaserealestate.core.data.repository.FavoritesRepository
import devlcc.io.kmmshowcaserealestate.core.data.repository.PropertiesRepository
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

fun initKoinIos(
    isDebug: Boolean,
//    userDefaults: NSUserDefaults,
    // Define AppInfo class to pass on platform-specific info(i.e. bundle ID, etc.)
): KoinApplication = initKoin(
    isDebug = isDebug,
    appModule = module {
        // TODO:: You may put any platform-specific dependencies in here...
//        single { userDefaults }
    }
)

internal actual val workerBgTaskManagerModule = module {
    single<WorkerBGTaskManager> {
        WorkerBGTaskManager(
            propertiesApiService = get(),
            propertiesLocalDao = get(),
            ioCoroutineDispatcher = get(),
            logger = get(),
        )
    }
}

/**
 * Helper class to resolve Core Data Module Dependencies
 */
@Suppress("unused")
object CoreDataResolver: KoinComponent {
    fun getLogger() = get<Logger>()
    fun getWorkerBgTaskManager() = get<WorkerBGTaskManager>()
    fun getPropertiesRepository() = get<PropertiesRepository>()
    fun getFavoritesRepository() = get<FavoritesRepository>()
}