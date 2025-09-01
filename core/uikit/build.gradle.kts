plugins {
    alias(default.plugins.android.library)
    alias(default.plugins.kotlin.android)
    alias(default.plugins.kotlin.compose)
}

android {
    namespace = "com.app.uikit"
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
    implementation(projects.core.resources)

    implementation(androidx.core.ktx)
    implementation(androidx.lifecycle.runtime.ktx)
    implementation(compose.androidx.activity.compose)
    implementation(platform(compose.androidx.compose.bom))
    implementation(compose.androidx.ui)
    implementation(compose.androidx.ui.graphics)
    implementation(compose.androidx.ui.tooling.preview)
    implementation(compose.androidx.material3)
}