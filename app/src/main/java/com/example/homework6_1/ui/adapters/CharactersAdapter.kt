package com.example.homework6_1.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.homework6_1.R
import com.example.homework6_1.data.model.Character
import com.example.homework6_1.databinding.CharacterItemBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CharactersAdapter :
    ListAdapter<Character, CharactersAdapter.CharacterViewHolder>(diffUtil) {

    inner class CharacterViewHolder(private val binding: CharacterItemBinding) : ViewHolder(binding.root) {
        fun bind(item: Character?) {
            with(binding) {
                tvName.text = item!!.name
                tvStatus.text = "${item!!.status} - ${item!!.species}"
                tvCurrentLocation.text = item.location.name
            }

            val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
            binding.tvCurrentTime.text = currentTime

            Glide.with(binding.root.context)
                .load(item?.image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageView)

            val circleDrawable = when (item?.status) {
                "Alive" -> R.drawable.circle_green
                "Dead" -> R.drawable.circle_red
                else -> R.drawable.circle_gray
            }
            binding.imgCircle.setImageResource(circleDrawable)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<Character>(){
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

}
