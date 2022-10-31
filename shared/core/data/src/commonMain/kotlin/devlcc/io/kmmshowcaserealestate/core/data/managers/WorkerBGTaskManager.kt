package devlcc.io.kmmshowcaserealestate.core.data.managers

import org.koin.core.module.Module

/**
 * KMM Blueprint for Background Worker/Task Execution
 * - For Android, using WorkManager
 * - For iOS, using BGTaskScheduler
 */
expect class WorkerBGTaskManager {

    /**
     * Fetch Property data from API server, then store into local database.
     */
    fun dispatchOperationFetchPropertyData()

}