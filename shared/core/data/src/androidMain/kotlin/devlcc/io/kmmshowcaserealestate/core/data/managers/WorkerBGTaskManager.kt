package devlcc.io.kmmshowcaserealestate.core.data.managers

import androidx.work.*
import devlcc.io.kmmshowcaserealestate.core.data.managers.workers.FetchPropertyDataWorker
import java.time.Duration
import java.util.concurrent.TimeUnit

actual class WorkerBGTaskManager(
    private val workManager: WorkManager,
) {

    private val workConstraints: Constraints =
        Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

    actual fun dispatchOperationFetchPropertyData() {

        val request = PeriodicWorkRequest
            .Builder(
                FetchPropertyDataWorker::class.java,
                60 * 60 * 1000L,
                TimeUnit.MILLISECONDS,
                60 * 60 * 1000L,
                TimeUnit.MILLISECONDS
            )
            .keepResultsForAtLeast(60 * 60 * 1000L, TimeUnit.MILLISECONDS)
            .setConstraints(workConstraints)
            .addTag(FetchPropertyDataWorker.UNIQUE_WORKER_NAME)
            .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 5, TimeUnit.SECONDS)
//            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()

        workManager.enqueue(request)

    }

}