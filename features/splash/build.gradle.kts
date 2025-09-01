plugins {
    alias(default.plugins.android.library)
    alias(default.plugins.kotlin.android)
    alias(default.plugins.kotlin.compose)
    alias(network.plugins.jetbrains.kotlin.serialization)
    alias(google.plugins.hilt)
    alias(google.plugins.ksp)
}

android {
    namespace = "com.app.features.splash"
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
    //projects
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.core.resources)

    implementation(androidx.core.ktx)
    implementation(androidx.appcompat)
    implementation(androidx.material)
    implementation(androidx.lifecycle.runtime.ktx)
    implementation(compose.androidx.activity.compose)
    implementation(platform(compose.androidx.compose.bom))
    implementation(network.kotlin.serialization.json)
    implementation(other.lottie.compose)

    implementation(compose.androidx.navigation.compose)
    implementation(compose.androidx.hilt.navigation.compose)

    //hilt
    implementation(google.hilt.android)
    ksp(google.hilt.android.compiler)

}