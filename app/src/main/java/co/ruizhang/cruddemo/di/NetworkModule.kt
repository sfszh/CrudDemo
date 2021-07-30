package co.ruizhang.cruddemo.di

import android.util.Log
import co.ruizhang.cruddemo.data.RepoApi
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single { createJson() }
    single { createHttpClient(get()) }
    single { RepoApi(get()) }
}

private fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }

private fun createHttpClient(json: Json) = HttpClient(Android) {
    install(JsonFeature) {
        serializer = KotlinxSerializer(json)
    }
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.v("Logger Ktor =>", message)
            }

        }
        level = LogLevel.ALL
    }

    install(ResponseObserver) {
        onResponse { response ->
            Log.d("HTTP status:", "${response.status.value}")
        }
    }

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }
}
