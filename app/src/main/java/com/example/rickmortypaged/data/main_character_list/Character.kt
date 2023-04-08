package com.example.rickmortypaged.data.main_character_list

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Character(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "status") val status: String,
    @Json(name = "species") val species: String,
    @Json(name = "gender") val gender: String,
    @Json(name = "image") val image: String,
    @Json(name = "location") val location: Location,
    @Json(name = "episode") var episodesString: List<String>,
) {
    var firstEpisode: String? = null
}