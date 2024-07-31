package com.example.homework6_1.ui.fragments.character

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework6_1.data.model.Character
import com.example.homework6_1.databinding.FragmentCharactersBinding
import com.example.homework6_1.ui.adapters.CharactersAdapter
import com.example.homework6_1.ui.fragments.BaseFragment
import com.example.homework6_1.ui.interfaces.OnClick
import com.example.homework6_1.utils.Resource
import com.example.homework6_1.utils.gone
import com.example.homework6_1.utils.showToast
import com.example.homework6_1.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment :
    BaseFragment<FragmentCharactersBinding>(FragmentCharactersBinding::inflate), OnClick {

    private val viewModel by viewModel<CharactersViewModel>()

    private val charactersAdapter by lazy {
        CharactersAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.character.observe(viewLifecycleOwner) { resource ->
            if (resource is Resource.Loading) binding.progressBar.visible() else binding.progressBar.gone()
            handleResource(resource,
                onSuccess = {data -> charactersAdapter.submitList(data)},
                onError = {message -> showToast(message)}
            )
        }
    }

    private fun setupRecyclerView() = with(binding.rvCharacters) {
        layoutManager = LinearLayoutManager(context)
        adapter = charactersAdapter
    }

    override fun onClick(character: Character) {
        val action =
            CharactersFragmentDirections.actionCharactersFragmentToCharactersDetailedFragment(
                character
            )
        findNavController().navigate(action)
    }
}
