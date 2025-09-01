pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "SatelliteComposeProject"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core")
include(":core:network")
include(":core:common")
include(":core:uikit")
include(":navigation")
include(":features:splash")
include(":features:home")
include(":features:home:data")
include(":features:home:domain")
include(":features:home:presentation")
include(":core:resources")
include(":features:detail")
include(":features:detail:data")
include(":features:detail:domain")
include(":features:detail:presentation")
