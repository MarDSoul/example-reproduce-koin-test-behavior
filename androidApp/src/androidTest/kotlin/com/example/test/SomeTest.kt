package com.example.test

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.TestActivity
import com.example.data.AuthRepository
import com.example.di.appModule
import com.example.presentation.App
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class SomeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

//    @get:Rule
//    val composeTestRule = createAndroidComposeRule<TestActivity>()

    @Before
    fun setup() {
        stopKoin() // need for clear Koin from ExampleApp initialization - dirty solution, will fix later
        startKoin {
            printLogger()
            modules(
                appModule,
                module { single<AuthRepository> { MockAuthRepositoryImpl() } }
            )
        }
        composeTestRule.setContent {
            App()
        }
    }

    @Test
    fun someTest() {
        with(composeTestRule) {
            // rule manually
            val authRepo = GlobalContext.get().get<AuthRepository>() as MockAuthRepositoryImpl
            authRepo.setUnauthorized()

            onNodeWithTag(AppTestTag.TAG_SCREEN).assertIsDisplayed()

            // nothing reaction because of Mock is in the ViewModel via Koin
            onNodeWithTag(AppTestTag.TAG_BUTTON).performClick()

            // rule manually
            authRepo.setAuthorized()
            waitForIdle()
            onNodeWithTag(AppTestTag.TAG_LABEL).assertIsDisplayed()
        }
    }

    @Test
    fun someOppositeTest() {
        with(composeTestRule) {
            // rule manually
            val authRepo = GlobalContext.get().get<AuthRepository>() as MockAuthRepositoryImpl
            authRepo.setAuthorized()

            onNodeWithTag(AppTestTag.TAG_SCREEN).assertIsDisplayed()

            // nothing reaction because of Mock is in the ViewModel via Koin
            onNodeWithTag(AppTestTag.TAG_BUTTON).performClick()

            // rule manually
            authRepo.setUnauthorized()
            waitForIdle()
            onNodeWithTag(AppTestTag.TAG_LABEL).assertIsNotDisplayed()
        }
    }
}