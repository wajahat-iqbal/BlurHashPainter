package com.example.imageloading.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imageloading.domain.domain_model.ImageLoadingDomainModel
import com.example.imageloading.domain.domain_model.ImagesDomainModel
import com.example.imageloading.domain.use_case.GetImagesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.getKoin


class ImageViewModel : ViewModel() {

    val TAG = "ImageViewModel"

    val getImagesUseCase: GetImagesUseCase = getKoin().get()

    private val _mutableState: MutableStateFlow<ImageLoadingState> =
        MutableStateFlow<ImageLoadingState>(
            ImageLoadingState.Idle
        )
    val mutableState = _mutableState.asStateFlow()

    fun getImages() {
        _mutableState.update {
            ImageLoadingState.Loading
        }
        viewModelScope.launch(Dispatchers.IO) {
            getImagesUseCase().collectLatest { result ->
                _mutableState.update {
                    ImageLoadingState.Success(result)
                }
            }
        }
    }
}

sealed class ImageLoadingState {
    object Idle : ImageLoadingState()
    object Loading : ImageLoadingState()
    data class Success(val data: ImageLoadingDomainModel) : ImageLoadingState()
    object Error : ImageLoadingState()
}


