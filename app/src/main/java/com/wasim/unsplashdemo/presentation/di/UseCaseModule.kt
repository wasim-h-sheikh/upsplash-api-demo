package com.wasim.unsplashdemo.presentation.di

import com.wasim.unsplashdemo.domain.repository.PhotosRepository
import com.wasim.unsplashdemo.domain.usecase.GetPhotosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetPhotosUseCase(photosRepository: PhotosRepository): GetPhotosUseCase {
        return GetPhotosUseCase(photosRepository)
    }

}