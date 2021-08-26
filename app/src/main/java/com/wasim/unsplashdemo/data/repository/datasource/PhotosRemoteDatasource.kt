package com.wasim.unsplashdemo.data.repository.datasource

import com.wasim.unsplashdemo.data.model.UnsplashResponse
import retrofit2.Response

interface PhotosRemoteDatasource {
   suspend fun getPhotos(page:Int,perPage:Int): Response<UnsplashResponse>
}