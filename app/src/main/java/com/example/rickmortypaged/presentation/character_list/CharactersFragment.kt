package com.example.rickmortypaged.presentation.character_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.rickmortypaged.R
import com.example.rickmortypaged.databinding.FragmentCharactersBinding
import com.example.rickmortypaged.presentation.detailed_information.CharacterFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding
        get() = _binding!!

    companion object {
        fun newInstance() = CharactersFragment()
    }

    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var myAdapter: CharacterAdapter
    private lateinit var itemViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        itemViewModel = ViewModelProvider(requireActivity())[ItemViewModel::class.java]
        _binding = FragmentCharactersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myAdapter = CharacterAdapter(itemViewModel)
        binding.recyclerView.adapter = myAdapter

        setData()

        itemViewModel.selectedCharacter.observe(viewLifecycleOwner) {
            if (itemViewModel.isOpenNewFragment.value!!)
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, CharacterFragment.newInstance())
                    ?.addToBackStack(null)
                    ?.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        itemViewModel.resetOpenFragmentFlag()
    }

    private fun setData() {
        lifecycleScope.launch {
            viewModel.pagedCharacters.collectLatest {
                myAdapter.submitData(it)
            }
        }
    }
}