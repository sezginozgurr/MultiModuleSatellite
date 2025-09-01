import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(default.plugins.android.application)
    alias(default.plugins.kotlin.android)
}

android {
    compileSdk = default.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = default.versions.minSdk.get().toInt()
        targetSdk = default.versions.targetSdk.get().toInt()
        versionCode = default.versions.versionCode.get().toInt()
        versionName = default.versions.versionName.get().toString()

        testInstrumentationRunner = default.versions.testInstrumentationRunner.get().toString()
    }

    buildTypes {
        release {
            val minifyEnabled = default.versions.isMinifyEnabled.get().toBoolean()
            val defaultProguardFile = default.versions.defaultProguardFile.get().toString()
            val proguardRules = default.versions.proguardRules.get().toString()

            isMinifyEnabled = minifyEnabled
            proguardFiles(getDefaultProguardFile(defaultProguardFile), proguardRules)
        }
    }
    val jvmVersion = default.versions.jvm.get().toString()
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(jvmVersion)
        targetCompatibility = JavaVersion.toVersion(jvmVersion)
    }
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.fromTarget(jvmVersion)
        }
    }
}

dependencies {
    implementation(androidx.bundles.default.lib)

    testImplementation(test.bundles.default.test)
    androidTestImplementation(test.bundles.default.android.test)
}