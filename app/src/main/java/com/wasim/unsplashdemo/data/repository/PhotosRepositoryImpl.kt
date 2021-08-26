package com.wasim.unsplashdemo.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.wasim.unsplashdemo.data.model.UnsplashPhoto
import com.wasim.unsplashdemo.data.repository.datasource.PhotosRemoteDatasource
import com.wasim.unsplashdemo.domain.repository.PhotosRepository


class PhotosRepositoryImpl(
    private val photosRemoteDatasource: PhotosRemoteDatasource
) : PhotosRepository {

    override suspend fun getPhotos(page:Int,perPage:Int): List<UnsplashPhoto>? {
        return getPhotosFromAPI(page,perPage)
    }


    private suspend fun getPhotosFromAPI(page:Int,perPage:Int): List<UnsplashPhoto>? {
        //lateinit var photos: List<UnsplashPhoto>
       return try {
            val response = photosRemoteDatasource.getPhotos(page,perPage)
           response.body()
        } catch (exception: Exception) {
            throw exception
        }
        //return photos
    }


}