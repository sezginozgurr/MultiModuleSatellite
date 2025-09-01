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
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("dev.panuszewski.typesafe-conventions") version "0.7.4"
}

typesafeConventions {
    // enable or disable auto dependency for every alias(...) plugin declaration in a convention plugin
    // set it to 'false' if you prefer to add plugin marker dependencies manually (you can use the pluginMarker helper method for that)
    autoPluginDependencies = true

    // whether to allow plugin usage for a top-level build
    // set it to 'true' only if you know what you're doing!
    allowTopLevelBuild = false

    // set it to true if you want to suppress the warning about pluginManagement { includeBuild(...) }
    suppressPluginManagementIncludedBuildWarning = false
}

rootProject.name = "build-logic"
include(":convention")