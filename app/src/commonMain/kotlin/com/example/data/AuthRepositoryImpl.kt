package com.example.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher
) : AuthRepository {
    init {
        println("AuthRepoImpl init()")
    }
    private val _authFow = MutableStateFlow(AuthState.IDLE)
    override val authFlow: Flow<AuthState> = _authFow.asStateFlow()

    override suspend fun login() {
        // login by backend, some logic, etc.
        withContext(ioDispatcher) {
            delay(2_000L)
            _authFow.value = AuthState.AUTHORIZED
        }
    }

    override suspend fun logout() {
        // logout by backend, some logic, etc.
        withContext(ioDispatcher) {
            delay(2_000L)
            _authFow.value = AuthState.UNAUTHORIZED
        }
    }
}