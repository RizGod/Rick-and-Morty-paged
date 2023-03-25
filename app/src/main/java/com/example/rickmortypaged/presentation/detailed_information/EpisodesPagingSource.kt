package com.example.rickmortypaged.presentation.detailed_information

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickmortypaged.data.detailed_information.Episode
import com.example.rickmortypaged.data.detailed_information.EpisodesRepository

class EpisodesPagingSource(
    private val episodes: List<Int>
) : PagingSource<Int, Episode>() {

    private val repository = EpisodesRepository()

    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        val page = params.key ?: episodes.first()
        return try {
            val episodeList = repository.getEpisodeList(episodes)
            LoadResult.Page(
                data = episodeList,
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}