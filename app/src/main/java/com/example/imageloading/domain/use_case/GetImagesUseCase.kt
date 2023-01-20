package com.example.imageloading.domain.use_case



import com.example.imageloading.data.remote.dto.asDomainModel
import com.example.imageloading.data.repository.Repository
import kotlinx.coroutines.flow.flow

class GetImagesUseCase(private val repository: Repository) {

    operator fun invoke() = flow {
        val response = repository.getImages().asDomainModel()
        emit(response)
    }

}