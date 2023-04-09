package com.example.rickmortypaged.presentation.detailed_information

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickmortypaged.data.detailed_information.Episode
import com.example.rickmortypaged.data.detailed_information.EpisodesRepository

class EpisodesPagingSource(
    private val episodes: List<Int>
) : PagingSource<Int, Episode>() {

    private val repository = EpisodesRepository()

    override fun getRefreshKey(state: PagingState<Int, Episode>): Int = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        val prevKey = params.key ?: 0
        val page = if (prevKey >= episodes.size) episodes.size - 1 else prevKey
        return try {
            val toIndex =
                if (page + params.loadSize >= episodes.size)
                    episodes.size
                else
                    page + params.loadSize
            val episodeList = repository.getEpisodeList(episodes.subList(page, toIndex))
            LoadResult.Page(
                data = episodeList,
                prevKey = if (page == 0) null else page - params.loadSize,
                nextKey = if (page == episodes.size - 1) null else page + params.loadSize
            )
        } catch (e: Exception) {
            Log.d("----------------Error---------------", "load: ${e.message}")
            LoadResult.Error(e)
        }
    }
}