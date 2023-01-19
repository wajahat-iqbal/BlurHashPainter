package com.example.imageloading.data.repository


import com.example.imageloading.data.remote.service.KtorService

class ImplRepository(
    private val ktorService: KtorService,
) : Repository {
    override suspend fun getImages() = ktorService.getImages()
}