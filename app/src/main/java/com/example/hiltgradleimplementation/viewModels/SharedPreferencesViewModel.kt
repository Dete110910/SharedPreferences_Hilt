package com.example.sharedpreferences_hilt.viewModels

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SharedPreferencesViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
): ViewModel(){

    private val _counter = MutableStateFlow(0)
    val counter : StateFlow<Int> = _counter.asStateFlow()

    fun increaseCounter(){
        var currentCounter = sharedPreferences.getInt("counter", 0)
        currentCounter++
        _counter.value = currentCounter
        updateCounter()
    }

    private fun updateCounter(){
        sharedPreferences.edit().putInt("counter", _counter.value).apply()
    }
}