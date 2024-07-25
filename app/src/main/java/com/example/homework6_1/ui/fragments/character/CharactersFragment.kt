package com.example.homework6_1.ui.fragments.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework6_1.R
import com.example.homework6_1.data.model.Character
import com.example.homework6_1.databinding.FragmentCharactersBinding
import com.example.homework6_1.ui.adapters.CharactersAdapter
import com.example.homework6_1.ui.interfaces.OnClick
import com.example.homework6_1.utils.Resource
import com.example.homework6_1.utils.gone
import com.example.homework6_1.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment(), OnClick {

    private val binding by lazy {
        FragmentCharactersBinding.inflate(layoutInflater)
    }

    private val viewModel: CharactersViewModel by viewModels()

    private val charactersAdapter by lazy {
        CharactersAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.character.observe(viewLifecycleOwner) { characters ->
            when (characters) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), characters.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    charactersAdapter.submitList(characters.data)
                }
                else -> {
                }
            }

            when (characters) {
                is Resource.Loading -> binding.progressBar.visible()
                else -> binding.progressBar.gone()
            }
        }
    }

    private fun setupRecyclerView() = with(binding.rvCharacters) {
        layoutManager = LinearLayoutManager(context)
        adapter = charactersAdapter
    }

    override fun onClick(character: Character) {
        viewModel.selectedCharacter(character)
        findNavController().navigate(R.id.action_charactersFragment_to_charactersDetailedFragment)
    }
}
