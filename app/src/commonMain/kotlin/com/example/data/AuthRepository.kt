package com.example.data

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val authFlow: Flow<AuthState>
    suspend fun login()
    suspend fun logout()
}