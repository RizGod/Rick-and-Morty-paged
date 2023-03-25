package com.example.rickmortypaged.data.main_character_list

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PagedCharacterList(
    @Json(name = "results") val results: List<Character>
)