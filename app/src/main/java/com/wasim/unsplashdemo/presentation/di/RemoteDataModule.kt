package com.wasim.unsplashdemo.presentation.di

import com.wasim.unsplashdemo.data.api.APIService
import com.wasim.unsplashdemo.data.repository.datasource.PhotosRemoteDatasource
import com.wasim.unsplashdemo.data.repository.datasourceImpl.PhotosRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {
    @Singleton
    @Provides
    fun providePhotosRemoteDatasource(apiService: APIService): PhotosRemoteDatasource {
        return PhotosRemoteDataSourceImpl(
            apiService
        )
    }

}