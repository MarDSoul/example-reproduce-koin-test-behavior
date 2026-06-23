package com.example.di

import com.example.data.AuthRepository
import com.example.data.AuthRepositoryImpl
import com.example.presentation.AppViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::AppViewModel)
    single<CoroutineDispatcher>(qualifier = named(IO_DISPATCHER)) {
        Dispatchers.IO
    }
    single<AuthRepository> { AuthRepositoryImpl(get(qualifier = named(IO_DISPATCHER))) }
}

const val IO_DISPATCHER = "IoDispatcher"
