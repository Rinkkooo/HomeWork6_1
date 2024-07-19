package com.example.homework6_1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework6_1.R
import com.example.homework6_1.databinding.ActivityMainBinding
import com.example.homework6_1.ui.fragments.CharactersFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CharactersFragment())
            .commit()
    }

}