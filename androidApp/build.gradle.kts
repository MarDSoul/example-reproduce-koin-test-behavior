plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "com.example"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

//        testInstrumentationRunner = "com.example.test.TestRunner"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        val javaVersion = JavaVersion.toVersion(libs.versions.java.version.get())
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
}

dependencies {
    implementation(projects.app)
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.uiToolingPreview)

    //test libs
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(dependencies.platform(libs.koin.bom))
    androidTestImplementation(libs.bundles.koin.test.android) {
//        exclude(group = "io.insert-koin", module = "koin-androidx-workmanager")
    }
    androidTestImplementation(libs.bundles.koin.ui) {
//        exclude(group = "io.insert-koin", module = "koin-androidx-workmanager")
    }
    androidTestImplementation(libs.compose.ui.test)
    debugImplementation(libs.compose.ui.test.manifest)
    androidTestImplementation(libs.kotlin.coroutines)
    androidTestImplementation(libs.kotlin.coroutines.test)
}
