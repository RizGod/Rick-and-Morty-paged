package com.example.rickmortypaged.presentation.detailed_information

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickmortypaged.data.main_character_list.Character

class CharacterViewModelFactory(private val character: Character) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            return CharacterViewModel(character) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}