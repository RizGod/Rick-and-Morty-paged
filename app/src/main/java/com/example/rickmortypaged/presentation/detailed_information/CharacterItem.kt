package com.example.rickmortypaged.presentation.detailed_information

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.example.rickmortypaged.R
import com.example.rickmortypaged.data.detailed_information.Episode
import com.example.rickmortypaged.data.main_character_list.Character
import com.example.rickmortypaged.presentation.detailed_information.ui.theme.AliveColor
import com.example.rickmortypaged.presentation.detailed_information.ui.theme.DeadColor
import com.example.rickmortypaged.presentation.detailed_information.ui.theme.RecyclerBackgroundColor
import com.example.rickmortypaged.presentation.detailed_information.ui.theme.UnknownColor

@Composable
fun CharacterItem(character: Character, items: LazyPagingItems<Episode>) {

    LazyColumn(
        Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .background(color = RecyclerBackgroundColor),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        item {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.icon)
            )
            Text(
                text = character.name,
                fontSize = 38.sp,
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.Bold
            )
            Text(text = "Live status:", color = colorResource(id = R.color.unknown_color))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.status_image),
                    contentDescription = null,
                    colorFilter = when (character.status) {
                        "Alive" -> ColorFilter.tint(AliveColor)
                        "Dead" -> ColorFilter.tint(DeadColor)
                        else -> ColorFilter.tint(UnknownColor)
                    },
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(10.dp)
                )
                Text(
                    text = character.status,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }

            Text(
                text = "Species and gender",
                color = colorResource(id = R.color.unknown_color),
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = "${character.species}(${character.gender})",
                color = colorResource(id = R.color.white)
            )

            Text(
                text = "Last known location:",
                color = colorResource(id = R.color.unknown_color),
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = character.location.name,
                color = colorResource(id = R.color.white)
            )

            Text(
                text = "First seen in:",
                color = colorResource(id = R.color.unknown_color),
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = character.firstEpisode ?: "Unknown",
                color = colorResource(id = R.color.white)
            )

            Text(
                text = "Episodes:", color = colorResource(id = R.color.white),
                modifier = Modifier.padding(top = 20.dp),
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
        }

        items(items) { episode ->
            episode?.let {
                EpisodeListItem(episode = it)
            }
        }

        items.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = items.loadState.refresh as LoadState.Error
                    item {
                        Column(modifier = Modifier.fillParentMaxSize()) {
                            e.error.localizedMessage?.let { Text(text = it) }
                            Button(onClick = { retry() }) {
                                Text(text = "Retry")
                            }
                        }

                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = items.loadState.append as LoadState.Error
                    item {
                        Column(
                            modifier = Modifier.fillParentMaxSize(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            e.error.localizedMessage?.let { Text(text = it) }
                            Button(onClick = { retry() }) {
                                Text(text = "Retry")
                            }
                        }
                    }
                }
            }
        }
    }
}