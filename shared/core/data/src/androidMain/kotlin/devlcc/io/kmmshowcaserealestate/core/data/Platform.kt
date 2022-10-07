@file:JvmName("PlatformJvmCoreData")

package devlcc.io.kmmshowcaserealestate.core.data

import android.content.Context
import devlcc.io.kmmshowcaserealestate.core.data.managers.WorkerBGTaskManager
import org.koin.core.KoinApplication
import org.koin.dsl.module

fun initKoinAndroid(
    isDebug: Boolean,
    context: Context,
): KoinApplication = initKoin(
    isDebug = isDebug,
    appModule = module {
        single { context }
    }
)

internal actual val workerBgTaskManagerModule = module {
    single<WorkerBGTaskManager> {
        WorkerBGTaskManager(
            workManager = get(),
        )
    }
}