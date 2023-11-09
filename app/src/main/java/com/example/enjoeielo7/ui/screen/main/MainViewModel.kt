package com.example.enjoeielo7.ui.screen.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enjoeielo7.repository.IUserRepository
import com.example.enjoeielo7.util.sharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val repository: IUserRepository
) : ViewModel() {

    private var token by application.sharedPreferences("token")

    init {
        Log.i("INFO", "Chamou api Repository")
        getUserRepositories()
    }

    fun getUserRepositories() {
        viewModelScope.launch {
            repository.getUserRepositories(
                authorization = token
            ).onSuccess { response ->
                Log.i("Teste", response.toString())
            }.onFailure { }
        }
    }
}