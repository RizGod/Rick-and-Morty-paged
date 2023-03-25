package com.example.rickmortypaged.presentation.detailed_information

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickmortypaged.data.detailed_information.Episode
import com.example.rickmortypaged.presentation.detailed_information.ui.theme.RecyclerItemColor
import com.example.rickmortypaged.presentation.detailed_information.ui.theme.UnknownColor

@Composable
fun EpisodeListItem(episode: Episode) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = RecyclerItemColor,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = episode.name,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = episode.airDate,
                    color = Color.White
                )
            }
            Text(
                text = episode.episode,
                color = UnknownColor,
                modifier = Modifier
                    .padding(start = 5.dp)
            )
        }

    }
}

@Preview
@Composable
fun preview() {
    EpisodeListItem(
        episode = Episode(
            name = "Pilot",
            airDate = "December 2, 2013",
            episode = "S01E01"
        )
    )
}