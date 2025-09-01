plugins {
    alias(default.plugins.android.application)
    alias(default.plugins.kotlin.android)
    alias(default.plugins.kotlin.compose)
    alias(google.plugins.hilt)
    alias(google.plugins.ksp)
}

android {
    namespace = "com.app.satellitecomposeproject"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.app.satellitecomposeproject"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(projects.core.uikit)
    implementation(projects.core.network)
    implementation(projects.core.common)
    implementation(projects.navigation)

    implementation(androidx.core.ktx)
    implementation(androidx.lifecycle.runtime.ktx)
    implementation(compose.androidx.activity.compose)
    implementation(platform(compose.androidx.compose.bom))
    implementation(compose.androidx.ui)
    implementation(compose.androidx.ui.graphics)
    implementation(compose.androidx.ui.tooling.preview)
    implementation(compose.androidx.material3)
    testImplementation(test.junit)
    androidTestImplementation(test.androidx.junit)
    androidTestImplementation(test.androidx.espresso.core)
    androidTestImplementation(platform(compose.androidx.compose.bom))
    debugImplementation(compose.androidx.ui.tooling)

    implementation(google.hilt.android)
    implementation(compose.androidx.hilt.navigation.compose)
    ksp(google.hilt.android.compiler)

    implementation(network.kotlin.serialization.json)
}