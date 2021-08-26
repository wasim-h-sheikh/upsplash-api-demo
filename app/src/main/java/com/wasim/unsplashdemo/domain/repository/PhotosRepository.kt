package com.wasim.unsplashdemo.domain.repository

import com.wasim.unsplashdemo.data.model.UnsplashPhoto
import com.wasim.unsplashdemo.data.model.UnsplashResponse


interface PhotosRepository {
    suspend fun getPhotos(page:Int,perPage:Int):List<UnsplashPhoto>?
}