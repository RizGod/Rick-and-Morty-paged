package com.example.rickmortypaged.data.detailed_information

import com.example.rickmortypaged.data.retrofit

class EpisodesRepository {

    suspend fun getEpisode(episode: Int): Episode =
        retrofit.episodeInfo(episode)

    suspend fun getEpisodeList(episodes: List<Int>): List<Episode> =
        episodes.map { getEpisode(it) }
}