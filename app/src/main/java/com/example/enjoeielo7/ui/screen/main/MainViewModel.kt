package com.example.enjoeielo7.ui.screen.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.example.enjoeielo7.models.repository.RepositoryItemModel
import com.example.enjoeielo7.models.repository.RepositoryVisibility
import com.example.enjoeielo7.repository.IUserRepository
import com.example.enjoeielo7.util.sharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val repository: IUserRepository
) : ViewModel() {

    private var token by application.sharedPreferences("token")

    private val _pagedRepository =
        MutableStateFlow<PagingData<RepositoryItemModel>>(PagingData.empty())

    val privatePagedRepository: StateFlow<PagingData<RepositoryItemModel>> =
        _pagedRepository
            .map { pagingData ->
                pagingData.filter { repositoryItem ->
                    repositoryItem.visibility == RepositoryVisibility.PRIVATE
                }
            }
            .stateIn(viewModelScope, SharingStarted.Eagerly, PagingData.empty())

    val publicPagedRepository: StateFlow<PagingData<RepositoryItemModel>> =
        _pagedRepository
            .map { pagingData ->
                pagingData.filter { repositoryItem ->
                    repositoryItem.visibility == RepositoryVisibility.PUBLIC
                }
            }
            .stateIn(viewModelScope, SharingStarted.Eagerly, PagingData.empty())

    init {
        getUserPagedRepositories()
    }

    private fun getUserPagedRepositories() {
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO) {
                repository.getPagedUserRepositories(token)
            }
            _pagedRepository.value = movies.cachedIn(viewModelScope).stateIn(viewModelScope).value
        }
    }
}