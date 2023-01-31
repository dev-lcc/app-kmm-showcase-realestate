@file:JvmName("KoinJvmInitKoin")

package devlcc.io.kmmshowcaserealestate.core.data.di

import android.content.Context
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
