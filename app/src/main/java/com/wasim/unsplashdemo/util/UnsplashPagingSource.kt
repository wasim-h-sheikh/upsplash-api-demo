package com.wasim.unsplashdemo.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wasim.unsplashdemo.data.model.UnsplashPhoto
import com.wasim.unsplashdemo.domain.usecase.GetPhotosUseCase
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class UnsplashPagingSource(
    private val getPhotosUseCase: GetPhotosUseCase
) : PagingSource<Int, UnsplashPhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = getPhotosUseCase.execute(position, params.loadSize)
                LoadResult.Page(
                    data = response!!,
                    prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (response.isEmpty()) null else position + 1
                )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}