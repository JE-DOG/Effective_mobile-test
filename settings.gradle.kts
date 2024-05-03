enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Effective_mobile_test"
include(":app")

//Core
include(":core")
include(":core:domain")
include(":core:data")
include(":core:feature")

//Feature
include(":feature")
include(":feature:placeholder")
