package com.example.rickmortypaged.presentation.character_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmortypaged.data.main_character_list.Character

class ItemViewModel : ViewModel() {

    private val _selectedCharacter = MutableLiveData<Character>()
    val selectedCharacter: LiveData<Character> get() = _selectedCharacter

    private val _isOpenNewFragment = MutableLiveData(false)
    val isOpenNewFragment: LiveData<Boolean> get() = _isOpenNewFragment

    fun selectCharacter(character: Character) {
        _isOpenNewFragment.value = true
        _selectedCharacter.value = character
    }

    fun resetOpenFragmentFlag() {
        _isOpenNewFragment.value = false
    }
}