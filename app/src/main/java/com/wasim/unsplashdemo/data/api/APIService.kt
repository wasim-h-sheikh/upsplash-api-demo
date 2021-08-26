package com.wasim.unsplashdemo.data.api

import com.wasim.unsplashdemo.BuildConfig
import com.wasim.unsplashdemo.data.model.UnsplashResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface APIService {
    companion object {
        const val BASE_URL = "https://api.unsplash.com"
        const val CLIENT_ID = BuildConfig.UNSPLASH_ACCESS_KEY
    }
    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("/photos")
    suspend fun getPhotos(@Query("page") page: Int,
                          @Query("per_page") perPage: Int,
                          @Query("order_by") orderBy: String="latest")
    : Response<UnsplashResponse>

}