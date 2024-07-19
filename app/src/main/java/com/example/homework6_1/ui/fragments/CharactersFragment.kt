package com.example.homework6_1.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework6_1.R
import com.example.homework6_1.databinding.FragmentCharactersBinding
import com.example.homework6_1.ui.adapters.CharactersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private val binding by lazy {
        FragmentCharactersBinding.inflate(layoutInflater)
    }

    private val viewModel: CharactersViewModel by viewModels()

    private val charactersAdapter by lazy {
        CharactersAdapter()
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
            charactersAdapter.submitList(characters)
        }

    }

    private fun setupRecyclerView() = with(binding.rvCharacters) {
        layoutManager = LinearLayoutManager(context)
        adapter = charactersAdapter
    }
}