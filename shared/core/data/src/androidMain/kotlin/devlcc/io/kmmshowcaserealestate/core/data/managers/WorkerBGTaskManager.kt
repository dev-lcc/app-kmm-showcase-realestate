package devlcc.io.kmmshowcaserealestate.core.data.managers

import androidx.work.*
import devlcc.io.kmmshowcaserealestate.core.data.managers.workers.FetchPropertyDataWorker
import java.time.Duration

actual class WorkerBGTaskManager(
    private val workManager: WorkManager,
) {

    private val workConstraints: Constraints =
        Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

    actual fun dispatchOperationFetchPropertyData() {

        val request = PeriodicWorkRequest.Builder(
            FetchPropertyDataWorker::class.java, Duration.ofSeconds(60 * 60)
        ).keepResultsForAtLeast(Duration.ofSeconds(60 * 60)).setConstraints(workConstraints)
            .addTag(FetchPropertyDataWorker.UNIQUE_WORKER_NAME)
            .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofSeconds(5))
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST).build()

        workManager.enqueue(request)

    }

}