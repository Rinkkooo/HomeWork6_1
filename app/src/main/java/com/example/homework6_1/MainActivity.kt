package com.example.homework6_1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.homework6_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val counterViewModel by viewModels<CounterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupClickListener()
        setupObservers()
    }

    private fun setupObservers() {
        counterViewModel.counterData.observe(this) { data ->
            binding.tvResult.text = data.toString()

        }
    }

    private fun setupClickListener() {
        binding.apply {
            btnIncrement.setOnClickListener {
                counterViewModel.onIncrementClick()
            }

            btnDecrement.setOnClickListener {
                counterViewModel.onDecrementClick()
            }
        }
    }

}