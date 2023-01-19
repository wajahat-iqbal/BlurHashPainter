package com.example.imageloading.data.remote.dto

import com.example.imageloading.domain.domain_model.ImageDomainModel

@kotlinx.serialization.Serializable
data class ImageDTO(
    val height: Int,
    val src: String,
    val width: Int
)

fun ImageDTO.asDomainModel() = ImageDomainModel(
    src, width, height
)
