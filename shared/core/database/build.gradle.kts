@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    android()
    iosX64() {
        binaries {
            framework {
                baseName = "CoreDatabase"
                isStatic = false
                linkerOpts("-lsqlite3")
            }
        }
    }
    iosArm64() {
        binaries {
            framework {
                baseName = "CoreDatabase"
                isStatic = false
                linkerOpts("-lsqlite3")
            }
        }
    }
    iosSimulatorArm64() {
        binaries {
            framework {
                baseName = "CoreDatabase"
                isStatic = false
                linkerOpts("-lsqlite3")
            }
        }
    }

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.RequiresOptIn")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                optIn("kotlin.experimental.ExperimentalObjCName")
            }
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":shared:core:model"))

                implementation(libs.coroutines.core)

                implementation(libs.koin.core)

                implementation(libs.kotlinx.serialization.json)

                implementation(libs.kotlinx.dateTime)

                implementation(libs.touchlab.stately)
                implementation(libs.touchlab.kermit)

                implementation(libs.sqlDelight.runtime)
                implementation(libs.sqlDelight.coroutinesExt)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.koin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.sqlDelight.android)
            }
        }
        val androidUnitTest by getting
        val androidInstrumentedTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation(libs.sqlDelight.native)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "devlcc.io.kmmshowcaserealestate.core.database"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
    }
}

sqldelight {
    database("KMMShowcaseRealEstateDb") {
        packageName = "devlcc.io.kmmshowcaserealestate.core.database"
    }
}