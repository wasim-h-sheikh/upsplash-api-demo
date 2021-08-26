package com.wasim.unsplashdemo.data.repository.datasourceImpl

import com.wasim.unsplashdemo.data.api.APIService
import com.wasim.unsplashdemo.data.model.UnsplashResponse
import com.wasim.unsplashdemo.data.repository.datasource.PhotosRemoteDatasource
import retrofit2.Response

class PhotosRemoteDataSourceImpl(
    private val apiService: APIService
) : PhotosRemoteDatasource {

    override suspend fun getPhotos(page:Int,perPage:Int)
            : Response<UnsplashResponse> = apiService.getPhotos(page,perPage)
}

