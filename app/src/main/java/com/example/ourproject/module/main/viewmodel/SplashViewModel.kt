package com.example.ourproject.module.main.viewmodel

import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewModelScope
import com.example.ourproject.core.base.BaseViewModel
import com.example.ourproject.core.datastore.DataStoreManager
import com.example.ourproject.module.main.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(
    userRepository: UserRepository
) : BaseViewModel() {

    private val _viewState = MutableStateFlow<SplashViewState>(SplashViewState.Idle)

    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000)
            userRepository.getTokenFlow().catch {
                if (it is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }.map {
                it[stringPreferencesKey(DataStoreManager.KEY_TOKEN)]
            }.collect {
                if (it.isNullOrEmpty()) {
                    _viewState.update {
                        SplashViewState.Success(LoginState.LoggedOut)
                    }
                } else {
                    _viewState.update {
                        SplashViewState.Success(LoginState.LoggedIn)
                    }
                }
            }
        }
    }

    sealed class SplashViewState {
        object Idle : SplashViewState()
        data class Success(val loginState: LoginState) : SplashViewState()
    }
}


enum class LoginState {
    LoggedIn,
    LoggedOut,
    Unknown
}
