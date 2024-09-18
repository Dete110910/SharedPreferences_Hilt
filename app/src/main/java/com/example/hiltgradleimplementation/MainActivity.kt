package com.example.hiltgradleimplementation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hiltgradleimplementation.databinding.ActivityMainBinding
import com.example.sharedpreferences_hilt.viewModels.SharedPreferencesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val sharedPreferencesViewModel: SharedPreferencesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        sharedPreferencesViewModel.increaseCounter()
        lifecycleScope.launch {
            sharedPreferencesViewModel.counter.collect{
                binding.tvCounter.text = it.toString()
            }
        }
    }
}