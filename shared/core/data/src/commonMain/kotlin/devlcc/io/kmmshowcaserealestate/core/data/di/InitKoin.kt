package devlcc.io.kmmshowcaserealestate.core.data.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

internal fun initKoin(
    isDebug: Boolean,
    appModule: Module,
): KoinApplication {
    val koinApplication = startKoin {
        modules(
            appModule,
            getCoreDataModule(isDebug)
        )
    }

    return koinApplication
}