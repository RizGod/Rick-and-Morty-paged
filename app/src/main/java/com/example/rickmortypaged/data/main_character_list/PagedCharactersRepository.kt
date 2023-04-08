package com.example.rickmortypaged.data.main_character_list

import com.example.rickmortypaged.data.retrofit

class PagedCharactersRepository {

    suspend fun getCharacterList(page: Int): List<Character> =
        retrofit.characterList(page).results

    suspend fun getFirstEpisodeName(character: Character): String =
        retrofit.episodeInfo(character.episodesString.first().substringAfterLast("/").toInt()).name
}