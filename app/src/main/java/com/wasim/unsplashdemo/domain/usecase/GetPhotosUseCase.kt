package com.wasim.unsplashdemo.domain.usecase

import com.wasim.unsplashdemo.data.model.UnsplashPhoto
import com.wasim.unsplashdemo.domain.repository.PhotosRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPhotosUseCase @Inject constructor(private val photosRepository: PhotosRepository) {
    suspend fun execute(page:Int,perPage:Int):List<UnsplashPhoto>? = photosRepository.getPhotos(page,perPage)
}