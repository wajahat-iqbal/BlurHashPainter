package com.example.imageloading.domain.use_case

import android.util.Log
import com.example.imageloading.data.remote.dto.BlurHashDecoder
import com.example.imageloading.data.remote.dto.asDomainModel
import com.example.imageloading.data.repository.Repository
import com.example.imageloading.domain.domain_model.ImageLoadingDomainModel
import kotlinx.coroutines.flow.flow

class GetImagesUseCase(private val repository: Repository) {

    operator fun invoke() = flow {
        val response = repository.getImages()
        val start = System.currentTimeMillis()
        Log.i("start",start.toString())
        val images =  response.asDomainModel()
        val time  = (System.currentTimeMillis()- start).toFloat()
        Log.i("End",time.toString())
        emit( ImageLoadingDomainModel(
            images = images,
            networkCallTime = "",
            "$time ms"
        ))
        BlurHashDecoder.clearCache()
    }

}