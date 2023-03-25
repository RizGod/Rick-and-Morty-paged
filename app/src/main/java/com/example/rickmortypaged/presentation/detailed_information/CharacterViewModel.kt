package com.example.rickmortypaged.presentation.detailed_information

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickmortypaged.data.detailed_information.Episode
import com.example.rickmortypaged.data.detailed_information.EpisodesRepository
import com.example.rickmortypaged.data.main_character_list.Character
import com.example.rickmortypaged.data.retrofit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val character: Character
) : ViewModel() {

    var firstEpisode: String? = null

    init {
        viewModelScope.launch {
            firstEpisode = retrofit.episodeInfo(
                character.episodesString.first().split("/").last().toInt()
            ).name
        }
    }

    val pagedEpisodes: Flow<PagingData<Episode>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            EpisodesPagingSource(character.episodesString.map { it.split("/").last().toInt() })
        }
    ).flow.cachedIn(viewModelScope)
}