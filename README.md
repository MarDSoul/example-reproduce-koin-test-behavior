# Reproducing an issue with Koin in Android tests in KMP project

This repository is for showing case of exception in the article: [Fixing RuntimeException caused by InitializationProvider in KMP/Koin integration tests](https://mardsoul.github.io/dev-in-action/articles/koin-kmp-android-test-exception/)

## Problem statement

The issue occurs during starting an application in tests. Get this exception:

```text
java.lang.RuntimeException: 
Unable to get provider androidx.startup.InitializationProvider: 
java.lang.ClassNotFoundException: 
Didn't find class "androidx.startup.InitializationProvider"
```

Regardless of the exception in cases the tests can be passed.

## Solution

The short - exclude `workmanager` module from the Koin test library:

```kotlin
androidTestImplementation(libs.bundles.koin.test.android) {
    exclude(group = "io.insert-koin", module = "koin-androidx-workmanager")
}
androidTestImplementation(libs.bundles.koin.ui) {
    exclude(group = "io.insert-koin", module = "koin-androidx-workmanager")
}
```