package com.example.homework6_1.ui.fragments.details

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.homework6_1.data.model.Character
import com.example.homework6_1.databinding.FragmentDetailsBinding
import com.example.homework6_1.ui.adapters.CharactersDetailedAdapter
import com.example.homework6_1.ui.fragments.BaseFragment
import com.example.homework6_1.utils.UiStates
import com.example.homework6_1.utils.gone
import com.example.homework6_1.utils.showToast
import com.example.homework6_1.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CharactersDetailedFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val viewModel by viewModel<CharactersDetailedViewModel>()
    private val args: CharactersDetailedFragmentArgs by navArgs()

    private val charactersDetailedAdapter by lazy {
        CharactersDetailedAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvEpisodes.adapter = charactersDetailedAdapter

        val character = args.character
        bind(character)
        charactersDetailedAdapter.submitList(character.episode)

        viewModel.charactersDetails.observe(viewLifecycleOwner) { resource ->
            binding.pbDetails.apply {
                if (resource is UiStates.Loading) visible() else gone()
            }
            when (resource) {
                is UiStates.Error -> {
                    showToast(resource.message)
                }
                else -> {
                }
            }
        }
    }


    private fun bind(character: Character) = with(binding) {
        tvName.text = character.name
        tvStatus.text = """${character.status} - ${character.species}"""
        tvLocation.text = character.location.name
        tvGender.text = character.gender
        val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val targetFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
        try {
            val date: Date? = originalFormat.parse(character.created)
            val formattedDate: String = targetFormat.format(date!!)
            tvCreatedTime.text = formattedDate
        } catch (e: ParseException) {
            e.printStackTrace()
            tvCreatedTime.text = character.created
        }
        Glide.with(root.context)
            .load(character.image)
            .into(imgDetailCharacter)

        val circleStatus = imgStatusOfCharacter.drawable
        val color = when (character.status) {
            "Alive" -> Color.GREEN
            "Dead" -> Color.RED
            else -> Color.GRAY
        }
        circleStatus?.setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN)
    }
}
