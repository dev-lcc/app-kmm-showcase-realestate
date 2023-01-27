val appName by extra { "KMM Real Estate" }
val versionName by extra { "0.0.1" }

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.rickclephas.kmp:kmp-nativecoroutines-gradle-plugin:1.0.0-ALPHA-4")
    }
}

// https://youtrack.jetbrains.com/issue/KTIJ-19369
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApp) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.sqlDelight) apply false
    alias(libs.plugins.googleKsp) apply false
    // alias(libs.plugins.kmpNativeCoroutines) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
