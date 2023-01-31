package devlcc.io.kmmshowcaserealestate.core.data.di

import co.touchlab.kermit.Logger
import devlcc.io.kmmshowcaserealestate.core.data.managers.WorkerBGTaskManager
import devlcc.io.kmmshowcaserealestate.core.data.repository.FavoritesRepository
import devlcc.io.kmmshowcaserealestate.core.data.repository.PropertiesRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

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
object CoreDataResolver : KoinComponent {
    fun getLogger() = get<Logger>()
    fun getWorkerBgTaskManager() = get<WorkerBGTaskManager>()
    fun getPropertiesRepository() = get<PropertiesRepository>()
    fun getFavoritesRepository() = get<FavoritesRepository>()
}