package com.example.rickmortypaged.data.detailed_information

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Episode(
    @Json(name = "name") val name: String,
    @Json(name = "air_date") val airDate: String,
    @Json(name = "episode") val episode: String
)
