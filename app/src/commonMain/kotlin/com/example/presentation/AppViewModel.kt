package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AuthRepository
import com.example.data.AuthState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppViewModel(
    private val authRepo: AuthRepository
) : ViewModel() {
    val authState: StateFlow<AuthState> = authRepo.authFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = AuthState.IDLE
        )

    fun login() {
        viewModelScope.launch {
            authRepo.login()
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepo.logout()
        }
    }
}