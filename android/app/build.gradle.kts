plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "devlcc.io.kmmshowcaserealestate.android.app"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "devlcc.io.kmmshowcaserealestate.android"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = rootProject.extra["versionName"].toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false

            manifestPlaceholders["appName"] = "${rootProject.extra["appName"]}-debug"
        }
        getByName("release") {
            isMinifyEnabled = true

            manifestPlaceholders["appName"] = "${rootProject.extra["appName"]}"
        }
    }
}

dependencies {
    implementation(project(":shared:core:data"))
    implementation(project(":shared:core:datastore"))
    implementation(project(":shared:core:model"))
    implementation(project(":shared:viewmodel"))

    implementation(libs.compose.ui)
    implementation(libs.compose.tooling)
    implementation(libs.compose.tooling.preview)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.activity)

    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.test)

    implementation(libs.touchlab.kermit)
}