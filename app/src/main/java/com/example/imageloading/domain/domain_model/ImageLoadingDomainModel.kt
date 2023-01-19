package com.example.imageloading.domain.domain_model

data class ImageLoadingDomainModel(
    val images :List<ImagesDomainModel> ,
    val networkCallTime : String = "",
    val blurHashToImageBitmapTime :String = ""
)
