package devlcc.io.kmmshowcaserealestate.kmmbridge.di

import devlcc.io.kmmshowcaserealestate.core.data.di.getCoreDataModule
import devlcc.io.kmmshowcaserealestate.viewmodel.di.getViewModelModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoinIos(
    isDebug: Boolean,
    // Define AppInfo class to pass on platform-specific info(i.e. bundle ID, etc.)
): KoinApplication = startKoin {
    modules(
        getCoreDataModule(isDebug),
        getViewModelModule()
    )
}