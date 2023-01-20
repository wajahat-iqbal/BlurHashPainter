package com.example.imageloading.data.remote.dto



import com.example.imageloading.domain.domain_model.ImagesDomainModel


@kotlinx.serialization.Serializable
data class ImagesDTO(
    val blurHash: String = "",
    val landscape: ImageDTO,
    val large: ImageDTO,
    val large2x: ImageDTO,
    val original: ImageDTO,
    val portrait: ImageDTO,
    val tiny: ImageDTO
)

fun ImagesDTO.asDomainModel() = ImagesDomainModel(
    blurHash = blurHash,
    landscape = landscape.asDomainModel(),
    large = large.asDomainModel(),
    large2x = large2x.asDomainModel(),
    original = original.asDomainModel(),
    portrait = portrait.asDomainModel(),
    tiny = tiny.asDomainModel()
)

fun List<ImagesDTO>.asDomainModel(): List<ImagesDomainModel> = this.map {
    it.asDomainModel()
}


