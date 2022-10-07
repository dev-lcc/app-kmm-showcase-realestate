package devlcc.io.kmmshowcaserealestate.core.data.managers

import co.touchlab.kermit.Logger
import devlcc.io.kmmshowcaserealestate.core.database.PropertyDao
import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import devlcc.io.kmmshowcaserealestate.core.network.PropertiesApiService
import kotlinx.coroutines.*
import platform.BackgroundTasks.BGAppRefreshTaskRequest
import platform.BackgroundTasks.BGProcessingTaskRequest
import platform.BackgroundTasks.BGTaskRequest
import platform.BackgroundTasks.BGTaskScheduler
import platform.Foundation.NSDate
import platform.Foundation.now

actual class WorkerBGTaskManager(
    internal val propertiesApiService: PropertiesApiService,
    internal val propertiesLocalDao: PropertyDao,
    internal val ioCoroutineDispatcher: CoroutineDispatcher,
    internal val logger: Logger,
) {

    private val bgTaskScheduler = BGTaskScheduler.sharedScheduler
    internal val bgTaskScope =
        /*CoroutineScope(SupervisorJob() + ioCoroutineDispatcher)*/MainScope() + CoroutineName("devlcc.io.kmmshowcaserealestate.core.data.managers.WorkerBGTaskManager")

    private val fetchPropertyDataTaskRequest: BGTaskRequest
        get() = BGProcessingTaskRequest/*BGAppRefreshTaskRequest*/(identifier = TASK_ID_FETCH_PROPERTY_DATA).also {
            it.setRequiresNetworkConnectivity(true)
            it.setEarliestBeginDate(NSDate.now)
        }

    /**
     * Invoked on AppDelegate.application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?)
     * - must be called after [registerTasks]
     */
    actual fun dispatchOperationFetchPropertyData() {
        logger.d("dispatchOperationFetchPropertyData() -> about to submit task request")
        try {
            bgTaskScheduler.submitTaskRequest(
                taskRequest = fetchPropertyDataTaskRequest,
                error = null,
            )
        } catch (err: Throwable) {
            err.printStackTrace()
            logger.e("dispatchOperationFetchPropertyData() -> error", err)
        }
    }

    /**
     * Invoked on AppDelegate.application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?)
     * - must be called before [dispatchOperationFetchPropertyData]
     */
    fun registerTasks() {
        try {
            bgTaskScheduler.registerForTaskWithIdentifier(
                identifier = TASK_ID_FETCH_PROPERTY_DATA,
                usingQueue = null/*dispatch_queue_global_t()*/
            ) { task ->
                logger.d("init::registerForTaskWithIdentifier[$TASK_ID_FETCH_PROPERTY_DATA]")
                task ?: return@registerForTaskWithIdentifier

                when (task.identifier) {
                    TASK_ID_FETCH_PROPERTY_DATA -> {
                        // Create an operation that performs the main part of the background task
                        val job = bgTaskScope.launch {
                            logger.d("init::registerForTaskWithIdentifier() -> about to start task")
                            try {
                                fetchPropertyData()
                                task.setTaskCompletedWithSuccess(true)
                            } catch (err: Throwable) {
                                err.printStackTrace()
                                logger.e(
                                    "init::registerForTaskWithIdentifier() -> error upon launch",
                                    err
                                )
                                task.setTaskCompletedWithSuccess(false)
                            }
                        }

                        // Provide an expiration handler for the background task
                        // that cancels the operation
                        task.setExpirationHandler {
                            logger.d("internalPerformFetchPropertyData() -> Task expired. About to cancel pending task..")
                            job.cancel()
                        }
                    }
                    else -> {
                        task.setTaskCompletedWithSuccess(false)
                        return@registerForTaskWithIdentifier
                    }
                }
            }

            // TODO:: Register other BG Task here...
        } catch (err: Throwable) {
            err.printStackTrace()
            logger.e("init::registerForTaskWithIdentifier() -> error", err)
        }

    }

    companion object {
        const val TASK_ID_FETCH_PROPERTY_DATA =
            "devlcc.io.kmmshowcaserealestate.core.data.managers.fetchPropertyDataIdentifier"
    }
}

/**
 * The actual function to perform Fetch Property Data operation.
 * - This can also be used on SwiftUI `.backgroundTask<D, R>(_: BackgroundTask<D, R>, action: (D) async -> R)`
 *
 * ```
 * let workerBgTaskManager = // ...
 *
 * WindowGroup {
 *      // ...
 * }
 * .backgroundTask(.appRefresh(WorkerBGTaskManager.TASK_ID_FETCH_PROPERTY_DATA)) {
 *      await workerBgTaskManager.fetchPropertyData()
 * }
 * ```
 */
suspend fun WorkerBGTaskManager.fetchPropertyData() {
    logger.d("fetchPropertyData() -> fetching properties from REST API service...")

    // Step #1: Fetch data from REST API service
    val allProperties = withContext(ioCoroutineDispatcher) {
        awaitAll(bgTaskScope.async { propertiesApiService.getPropertiesForSale() },
            bgTaskScope.async { propertiesApiService.getPropertiesForRent() },
            bgTaskScope.async { propertiesApiService.getPropertiesSold() }).fold(
            listOf<Property>()
        ) { accumulated, subList -> accumulated + subList }
    }

    logger.d("fetchPropertyData() -> storing data(${allProperties.size} count) into local database...")
    // Step #2: Store into local database
    propertiesLocalDao.upsert(
        batchCount = 10, properties = allProperties.toTypedArray()
    )
}

