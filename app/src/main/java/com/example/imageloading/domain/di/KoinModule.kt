package com.example.imageloading.domain.di


import com.example.imageloading.data.remote.service.ImplKtorService
import com.example.imageloading.data.remote.service.KtorService
import com.example.imageloading.data.repository.ImplRepository
import com.example.imageloading.data.repository.Repository
import com.example.imageloading.domain.use_case.GetImagesUseCase
import com.example.imageloading.presentation.ImageViewModel

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.utils.io.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module


val module = module {
    single<KtorService> {
        ImplKtorService(get())
    }
    single<Repository>{
        ImplRepository(get())
    }
    single { GetImagesUseCase(get()) }
    single { createJson() }
    single {
        createHttpClient(
            get() , true
        )
    }
}

fun createHttpClient(
    json: Json, enableNetworkLogs: Boolean
) = HttpClient {

    //exception handling
    install(HttpTimeout) {
        requestTimeoutMillis = 50000
        connectTimeoutMillis = 50000
        socketTimeoutMillis = 50000
    }

    install(HttpRequestRetry) {
        maxRetries = 10
        retryIf { _, response ->
            !response.status.isSuccess()
        }
        retryOnExceptionIf { _, cause ->
            cause is HttpRequestTimeoutException || cause is CancellationException
        }
        delayMillis {
            3000L
        } // retries in 3, 6, 9, etc. seconds
    }

    install(HttpCallValidator) {
        handleResponseException { cause ->
            println("exception $cause")
        }
    }

    HttpResponseValidator {
        validateResponse { response ->
            when (response.status) {
                HttpStatusCode.Unauthorized -> {

                }
                else -> Unit
            }
        }
    }

    install(ContentNegotiation) {
        json(json)
    }
    if (enableNetworkLogs) {
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }
}

fun createJson() = Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = true
    prettyPrint = true
    coerceInputValues = true
}





