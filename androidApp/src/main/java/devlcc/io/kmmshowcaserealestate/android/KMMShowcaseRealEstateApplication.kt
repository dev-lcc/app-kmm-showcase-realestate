package devlcc.io.kmmshowcaserealestate.android

import android.app.Application
import devlcc.io.kmmshowcaserealestate.core.data.getCoreDataModule
import devlcc.io.kmmshowcaserealestate.core.datastore.getCoreDatastoreModule
import devlcc.io.kmmshowcaserealestate.viewmodel.di.getViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KMMShowcaseRealEstateApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(
                getCoreDataModule(isDebug = BuildConfig.DEBUG),
                getCoreDatastoreModule(),
                getViewModelModule(),
            )
        }

    }

}