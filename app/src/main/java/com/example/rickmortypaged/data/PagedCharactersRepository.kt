package com.example.rickmortypaged.data

class PagedCharactersRepository {

    suspend fun getCharacterList(page: Int): List<Character> =
        retrofit.characterList(page).results
}