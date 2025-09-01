// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias (default.plugins.android.application) apply false
    alias (default.plugins.kotlin.android) apply false
    alias (default.plugins.kotlin.compose) apply false
    alias (default.plugins.android.library) apply false
    alias (google.plugins.hilt) apply false
    alias (google.plugins.ksp) apply false
    alias (network.plugins.jetbrains.kotlin.serialization) apply false
}