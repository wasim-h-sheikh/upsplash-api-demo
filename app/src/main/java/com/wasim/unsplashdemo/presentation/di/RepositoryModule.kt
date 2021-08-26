package com.wasim.unsplashdemo.presentation.di


import com.wasim.unsplashdemo.data.repository.PhotosRepositoryImpl
import com.wasim.unsplashdemo.data.repository.datasource.PhotosRemoteDatasource
import com.wasim.unsplashdemo.domain.repository.PhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providePhotosRepository(
        photosRemoteDatasource: PhotosRemoteDatasource
    ): PhotosRepository {

        return PhotosRepositoryImpl(
            photosRemoteDatasource
        )
    }

}