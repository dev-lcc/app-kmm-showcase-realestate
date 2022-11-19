pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "KMM_Showcase_Real_Estate"
include(":shared:core:data")
include(":shared:core:datastore")
include(":shared:core:model")
include(":shared:core:network")
include(":shared:core:database")
include(":shared:viewmodel")

include(":android:app")
include(":android:core:designsystem")
