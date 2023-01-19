package com.example.imageloading.domain.domain_model

import androidx.compose.ui.graphics.ImageBitmap

data class ImagesDomainModel(
    var bitmap: ImageBitmap? = null,
    val blurHash: String?  = "",
    val landscape: ImageDomainModel = ImageDomainModel(),
    val large: ImageDomainModel = ImageDomainModel(),
    val large2x: ImageDomainModel = ImageDomainModel(),
    val original: ImageDomainModel = ImageDomainModel(),
    val portrait: ImageDomainModel = ImageDomainModel(),
    val tiny: ImageDomainModel = ImageDomainModel(),
)
