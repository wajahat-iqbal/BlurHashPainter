package com.example.imageloading.domain.domain_model


data class ImagesDomainModel(
    val blurHash: String?  = "",
    val landscape: ImageDomainModel = ImageDomainModel(),
    val large: ImageDomainModel = ImageDomainModel(),
    val large2x: ImageDomainModel = ImageDomainModel(),
    val original: ImageDomainModel = ImageDomainModel(),
    val portrait: ImageDomainModel = ImageDomainModel(),
    val tiny: ImageDomainModel = ImageDomainModel(),
)
