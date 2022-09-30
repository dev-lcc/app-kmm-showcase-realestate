package devlcc.io.kmmshowcaserealestate.core.data

import co.touchlab.kermit.Logger
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val coreDataModule = module {

    single<Logger> { provideLogger() }
    single<Json> { provideJson() }

}

internal fun provideLogger(): Logger =
    Logger.withTag("KMMRealEstateShowcase")

internal fun provideJson(): Json =
    Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }