package com.example.rickmortypaged.presentation.character_list

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import com.bumptech.glide.Glide
import com.example.rickmortypaged.R
import com.example.rickmortypaged.data.main_character_list.Character
import com.example.rickmortypaged.databinding.ItemCharacterBinding

class CharacterAdapter(private val itemViewModel: ItemViewModel) :
    PagingDataAdapter<Character, MyViewHolder>(MyDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            item?.let {
                nameTextView.text = it.name
                statusSpeciesTextView.text = "${it.status} - ${it.species}"
                lastLocationTextView.text = it.location.name
                when (it.status) {
                    "Alive" -> statusColorImageView.setColorFilter(
                        ContextCompat.getColor(
                            statusColorImageView.context,
                            R.color.alive_color
                        ), PorterDuff.Mode.SRC_IN
                    )
                    "Dead" -> statusColorImageView.setColorFilter(
                        ContextCompat.getColor(
                            statusColorImageView.context,
                            R.color.dead_color
                        ), PorterDuff.Mode.SRC_IN
                    )
                    else -> statusColorImageView.setColorFilter(
                        ContextCompat.getColor(
                            statusColorImageView.context,
                            R.color.unknown_color
                        ), PorterDuff.Mode.SRC_IN
                    )
                }
                Glide
                    .with(image.context)
                    .load(item.image)
                    .error(R.drawable.icon)
                    .into(image)
                holder.itemView.setOnClickListener {
                    itemViewModel.selectCharacter(item)
                }
            }
        }
    }
}