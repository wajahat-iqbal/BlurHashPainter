package com.example.imageloading.data.repository


import com.example.imageloading.data.remote.dto.ImagesDTO

interface Repository {
    suspend fun getImages():List<ImagesDTO>
}