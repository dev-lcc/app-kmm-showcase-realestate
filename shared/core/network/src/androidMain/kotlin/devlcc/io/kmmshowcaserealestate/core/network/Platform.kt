@file:JvmName("PlatformJvm")

package devlcc.io.kmmshowcaserealestate.core.network

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

actual val platformNetworkModule = module {
    single<HttpClientEngine> {
        OkHttp.create {}
    }
}
