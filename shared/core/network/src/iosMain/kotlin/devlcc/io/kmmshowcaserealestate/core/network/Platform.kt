package devlcc.io.kmmshowcaserealestate.core.network

import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*
import org.koin.dsl.module

actual val platformNetworkModule = module {
    single<HttpClientEngine> {
        Darwin.create {}
    }
}