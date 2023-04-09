package com.example.rickmortypaged.presentation.detailed_information

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickmortypaged.presentation.character_list.ItemViewModel

class CharacterFragment : Fragment() {

    private val itemViewModel: ItemViewModel by lazy {
        ViewModelProvider(requireActivity())[ItemViewModel::class.java]
    }

    private val viewModel: CharacterViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(CharacterViewModel::class.java))
                    return CharacterViewModel(itemViewModel) as T
                throw IllegalArgumentException("Unknown class")
            }
        }
    }

    companion object {
        fun newInstance() = CharacterFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CharacterItem(
                character = itemViewModel.selectedCharacter.value!!,
                items = viewModel.pagedEpisodes.collectAsLazyPagingItems()
            )
        }
    }
}

