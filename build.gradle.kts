val appName by extra { "KMM Real Estate" }
val versionName by extra { "0.0.1" }

//// Configure module names for libraries based on Gradle's group name + module name.
//allprojects {
//    pluginManager.withPlugin("kotlin-multiplatform") {
//        val kotlinExtension = project.extensions.getByName("kotlin")
//                as org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
//        val uniqueName = "${project.group}.${project.name}"
//
//        kotlinExtension.targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java) {
//            compilations["main"].kotlinOptions.freeCompilerArgs += listOf("-module-name", uniqueName)
//        }
//    }
//}

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
    alias(libs.plugins.kmpNativeCoroutines) apply false
    alias(libs.plugins.googleKsp) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
