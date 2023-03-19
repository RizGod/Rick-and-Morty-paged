package com.example.rickmortypaged.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.rickmortypaged.data.Character

class MyDiffUtilCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem.name == newItem.name
}