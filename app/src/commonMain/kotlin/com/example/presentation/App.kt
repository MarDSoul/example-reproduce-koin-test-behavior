package com.example.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.Greeting
import com.example.data.AuthState
import com.example.test.AppTestTag
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import reproducefailure.app.generated.resources.Res
import reproducefailure.app.generated.resources.compose_multiplatform

@Composable
fun App(
    viewModel: AppViewModel = koinViewModel<AppViewModel>()
) {
    val authState by viewModel.authState.collectAsState()

    MaterialTheme {
        var showContent by remember(authState) { mutableStateOf(authState == AuthState.AUTHORIZED) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize()
                .testTag(AppTestTag.TAG_SCREEN),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = { if (authState != AuthState.AUTHORIZED) viewModel.login() else viewModel.logout() },
                modifier = Modifier.testTag(AppTestTag.TAG_BUTTON)
            ) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = null,
                        modifier = Modifier.testTag(AppTestTag.TAG_LABEL)
                    )
                    Text("Compose: $greeting")
                }
            }
        }
    }
}