package com.example.imageloading.domain.domain_model

import android.graphics.Bitmap


data class ImagesDomainModel(
    var bitmap: Bitmap? = null,
    val blurHash: String?  = "",
    val landscape: ImageDomainModel = ImageDomainModel(),
    val large: ImageDomainModel = ImageDomainModel(),
    val large2x: ImageDomainModel = ImageDomainModel(),
    val original: ImageDomainModel = ImageDomainModel(),
    val portrait: ImageDomainModel = ImageDomainModel(),
    val tiny: ImageDomainModel = ImageDomainModel(),
)
