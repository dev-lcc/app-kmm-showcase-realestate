package devlcc.io.kmmshowcaserealestate.android.app

import android.app.Application
import devlcc.io.kmmshowcaserealestate.core.data.di.initKoinAndroid
import devlcc.io.kmmshowcaserealestate.core.data.managers.WorkerBGTaskManager
import devlcc.io.kmmshowcaserealestate.viewmodel.di.getViewModelModule

class KMMShowcaseRealEstateApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val koinApp = initKoinAndroid(
            isDebug = BuildConfig.DEBUG,
            context = this@KMMShowcaseRealEstateApplication,
        ).apply {
            modules(
                listOf(
                    getViewModelModule()
                )
            )
        }

//        val bgTaskManager = koinApp.koin.get<WorkerBGTaskManager>()
//        bgTaskManager.dispatchOperationFetchPropertyData()

    }

}