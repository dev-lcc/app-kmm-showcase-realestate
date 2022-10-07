package devlcc.io.kmmshowcaserealestate.core.data.managers.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import devlcc.io.kmmshowcaserealestate.core.database.PropertyDao
import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import devlcc.io.kmmshowcaserealestate.core.network.PropertiesApiService
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FetchPropertyDataWorker(
    private val context: Context,
    private val params: WorkerParameters,
) : CoroutineWorker(context, params), KoinComponent {

    private val propertiesApiService: PropertiesApiService by inject()
    private val propertiesLocalDao: PropertyDao by inject()
    private val workerScope =
        MainScope() + CoroutineName(FetchPropertyDataWorker::class.java.simpleName)

    override suspend fun doWork(): Result {

        return try {
            // Step #1: Fetch data from REST API service
            val allProperties =
                awaitAll(workerScope.async(Dispatchers.IO) { propertiesApiService.getPropertiesForSale() },
                    workerScope.async(Dispatchers.IO) { propertiesApiService.getPropertiesForRent() },
                    workerScope.async(Dispatchers.IO) { propertiesApiService.getPropertiesSold() }).fold(
                        listOf<Property>()
                    ) { accumulated, subList -> accumulated + subList }

            // Step #2: Store into local database
            workerScope.launch(Dispatchers.IO) {
                propertiesLocalDao.upsert(
                    batchCount = 10, properties = allProperties.toTypedArray()
                )
            }

            Result.success()
        } catch (err: Throwable) {
            err.printStackTrace()
            Result.retry()
            Result.failure()
        }
    }

    companion object {
        val UNIQUE_WORKER_NAME =
            "${FetchPropertyDataWorker::class.java.simpleName}.UNIQUE_WORKER_NAME"

    }
}