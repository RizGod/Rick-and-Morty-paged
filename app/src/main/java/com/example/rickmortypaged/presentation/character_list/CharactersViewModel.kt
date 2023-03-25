package com.example.rickmortypaged.presentation.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickmortypaged.data.main_character_list.Character
import kotlinx.coroutines.flow.Flow

class CharactersViewModel : ViewModel() {

    val pagedCharacters: Flow<PagingData<Character>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { CharactersPagingSource() }
    ).flow.cachedIn(viewModelScope)
}