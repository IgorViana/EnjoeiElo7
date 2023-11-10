package com.example.enjoeielo7.ui.screen.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enjoeielo7.models.repository.RepositoryItemModel
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

    private val _repositories = MutableLiveData<List<RepositoryItemModel>>()
    val repositories: LiveData<List<RepositoryItemModel>> get() = _repositories

    init {
        getUserRepositories()
    }

    private fun getUserRepositories() {
        viewModelScope.launch {
            repository.getUserRepositories(
                authorization = token
            ).onSuccess { response ->
                _repositories.postValue(response)
            }.onFailure { }
        }
    }
}