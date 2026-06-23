package com.example.test

import android.util.Log
import com.example.data.AuthRepository
import com.example.data.AuthState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class MockAuthRepositoryImpl() : AuthRepository {
    init {
        Log.d("MockAuthRepository", "init()")
    }

    private val _authStateFlow = MutableStateFlow(AuthState.IDLE)
    override val authFlow: Flow<AuthState> = _authStateFlow.asStateFlow()
    override suspend fun login() {}
    override suspend fun logout() {}

    fun setAuthorized() {
        _authStateFlow.value = AuthState.AUTHORIZED
    }

    fun setUnauthorized() {
        _authStateFlow.value = AuthState.UNAUTHORIZED
    }
}
