@file:JvmName("KoinJvmCoreData")

package devlcc.io.kmmshowcaserealestate.core.data.di

import android.content.Context
import androidx.work.WorkManager
import devlcc.io.kmmshowcaserealestate.core.data.managers.WorkerBGTaskManager
import org.koin.dsl.module

internal actual val workerBgTaskManagerModule = module {

    single<WorkManager> {
        WorkManager.getInstance(get<Context>())
    }

    single<WorkerBGTaskManager> {
        WorkerBGTaskManager(
            workManager = get(),
        )
    }
}