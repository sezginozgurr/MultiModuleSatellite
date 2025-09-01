plugins {
    alias(default.plugins.android.library)
    alias(default.plugins.kotlin.android)
    alias(default.plugins.kotlin.compose)
    alias(network.plugins.jetbrains.kotlin.serialization)
    alias(google.plugins.hilt)
    alias(google.plugins.ksp)
}

android {
    namespace = "com.app.features.home.presentation"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

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
}

dependencies {
    //project
    implementation(projects.core.uikit)
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.features.home.domain)
    implementation(projects.features.home.data)

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
    implementation(network.kotlin.serialization.json)

    implementation(compose.androidx.hilt.navigation.compose)
    implementation(compose.androidx.navigation.compose)

    //hilt
    implementation(google.hilt.android)
    ksp(google.hilt.android.compiler)
}