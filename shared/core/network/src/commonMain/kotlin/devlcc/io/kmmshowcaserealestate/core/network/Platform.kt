@file:JvmName("PlatformKmm")

package devlcc.io.kmmshowcaserealestate.core.network

import co.touchlab.kermit.Logger
import devlcc.io.kmmshowcaserealestate.core.network.api.PropertiesApiService
import devlcc.io.kmmshowcaserealestate.core.network.api.ktor.PropertiesApiServiceImpl
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.jvm.JvmName
import io.ktor.client.plugins.logging.Logger as KtorLogger

fun getCoreNetworkModule(
    isDebug: Boolean
) = module {
    single<HttpClient> {
        provideHttpClient(
            isDebug = isDebug,
            engine = get(),
            json = get(),
            logger = get(),
        )
    }

    // TODO:: List API service(s) here...
    single<PropertiesApiService> {
        PropertiesApiServiceImpl(
            client = get(),
        )
    }

}.apply {
    includes(platformNetworkModule)
}

fun provideHttpClient(
    isDebug: Boolean,
    engine: HttpClientEngine,
    json: Json,
    logger: Logger,
): HttpClient = HttpClient(engine) {
    expectSuccess = true
    developmentMode = isDebug

    install(ContentNegotiation) {
        this@install.json(json)
    }

    install(Logging) {
        this@install.logger = object : KtorLogger {
            override fun log(message: String) {
                logger.v(message)
            }
        }

        level = if (isDebug) {
            LogLevel.ALL
        } else {
            LogLevel.INFO
        }
    }

    install(HttpTimeout) {
        val timeout = 30000L
        connectTimeoutMillis = timeout
        requestTimeoutMillis = timeout
        socketTimeoutMillis = timeout
    }

    // TODO:: Add Custom Interceptor(s) in here...
}

internal expect val platformNetworkModule: Module