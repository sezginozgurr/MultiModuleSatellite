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

    versionCatalogs {
        create("default") {
            from(files("gradle/libDefault.versions.toml"))
        }
        create("androidx") {
            from(files("gradle/libAndroidx.versions.toml"))
        }
        create("compose") {
            from(files("gradle/libCompose.versions.toml"))
        }
        create("test") {
            from(files("gradle/libTests.versions.toml"))
        }
        create("google") {
            from(files("gradle/libGoogle.versions.toml"))
        }
        create("jetbrains") {
            from(files("gradle/libJetbrains.versions.toml"))
        }
        create("other") {
            from(files("gradle/libOther.versions.toml"))
        }
        create("network") {
            from(files("gradle/libNetwork.versions.toml"))
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
includeBuild("build-logic")

rootProject.name = "SatelliteComposeProject"
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
