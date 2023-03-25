package com.example.rickmortypaged.presentation.character_list

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickmortypaged.data.main_character_list.Character
import com.example.rickmortypaged.data.main_character_list.PagedCharactersRepository

class CharactersPagingSource : PagingSource<Int, Character>() {

    private val repository = PagedCharactersRepository()
    override fun getRefreshKey(state: PagingState<Int, Character>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: FIRST_PAGE
        return try {
            val charactersPaged = repository.getCharacterList(page)
            LoadResult.Page(
                data = charactersPaged,
                prevKey = params.key?.let { it - 1 },
                nextKey = if (charactersPaged.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            Log.d("Exception is ", "load: ${e.message}")
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}