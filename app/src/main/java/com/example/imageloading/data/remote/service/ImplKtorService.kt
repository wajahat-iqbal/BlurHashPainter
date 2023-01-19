package com.example.imageloading.data.remote.service

import com.example.imageloading.data.remote.dto.ImagesDTO

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class ImplKtorService(private val httpClient: HttpClient) : KtorService {
    val TAG = "KtorService"
    override suspend fun getImages(): List<ImagesDTO>  =
        httpClient.get(" https://api.npoint.io/507946c2a6255002cec9") {
            contentType(ContentType.Application.Json)
        }.body()

}