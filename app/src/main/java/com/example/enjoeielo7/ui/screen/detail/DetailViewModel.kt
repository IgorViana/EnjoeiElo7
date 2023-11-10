package com.example.enjoeielo7.ui.screen.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enjoeielo7.models.repository.RepositoryDetailModel
import com.example.enjoeielo7.repository.IUserRepository
import com.example.enjoeielo7.util.sharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle,
    private val repository: IUserRepository
) : ViewModel() {

    private var token by application.sharedPreferences("token")
    private val owner: String = savedStateHandle.get<String>("owner").orEmpty()
    private val repositoryName: String = savedStateHandle.get<String>("repositoryName").orEmpty()

    private val _repositoryDetail = MutableLiveData<RepositoryDetailModel>()
    val repositoryDetail: LiveData<RepositoryDetailModel> get() = _repositoryDetail

    init {
        getRepositoryData()
    }

    private fun getRepositoryData() {
        viewModelScope.launch {
            repository.getRepositoryDetail(
                owner = owner,
                repositoryName = repositoryName,
                authorization = token
            ).onSuccess { response ->
                _repositoryDetail.postValue(response)
            }.onFailure { }
        }
    }
}