plugins {
    alias(default.plugins.android.library)
    alias(default.plugins.kotlin.android)
    alias(google.plugins.hilt)
    alias(google.plugins.ksp)
}

android {
    namespace = "com.app.feature.detail.data"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.features.detail.domain)

    implementation(compose.androidx.hilt.navigation.compose)
    implementation(google.hilt.android)
    ksp(google.hilt.android.compiler)

    implementation(network.bundles.network)
}