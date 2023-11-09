package com.example.enjoeielo7.ui.screen.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enjoeielo7.repository.ILoginRepository
import com.example.enjoeielo7.util.CLIENT_ID
import com.example.enjoeielo7.util.CLIENT_SECRET
import com.example.enjoeielo7.util.sharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val repository: ILoginRepository
) : ViewModel() {

    private var code by application.sharedPreferences("code")
    private var state by application.sharedPreferences("state")
    private var token by application.sharedPreferences("token")

    private val _onGetTokenSuccess = MutableLiveData(false)
    val onGetTokenSuccess: LiveData<Boolean> get() = _onGetTokenSuccess

   /* private val _onGetApiTokenTriggered = MutableLiveData(false)*/
    var onGetApiTokenTriggered: Boolean = false

    fun setOnGetApiTokenTriggered() {
        onGetApiTokenTriggered = true
    }
    fun getApiToken() {
        viewModelScope.launch {
            repository.getToken(
                clientId = CLIENT_ID,
                clientSecret = CLIENT_SECRET,
                code = code,
                state = state
            ).onSuccess { tokenResponse ->
                token = tokenResponse.accessToken.orEmpty()
                _onGetTokenSuccess.postValue(true)
            }.onFailure { }
        }
    }
}