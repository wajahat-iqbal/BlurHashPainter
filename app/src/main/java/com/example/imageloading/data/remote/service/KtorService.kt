package com.example.imageloading.data.remote.service


import com.example.imageloading.data.remote.dto.ImagesDTO

interface KtorService  {
    suspend fun getImages() :List<ImagesDTO>
}