package com.example.rickmortypaged.presentation.detailed_information

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickmortypaged.data.detailed_information.Episode
import com.example.rickmortypaged.presentation.character_list.ItemViewModel
import kotlinx.coroutines.flow.Flow

class CharacterViewModel(
    itemViewModel: ItemViewModel
) : ViewModel() {

    var character = itemViewModel.selectedCharacter.value!!

    val pagedEpisodes: Flow<PagingData<Episode>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            EpisodesPagingSource(character.episodesString.map {
                it.substringAfterLast("/", "").toInt()
            })
        }
    ).flow.cachedIn(viewModelScope)
}