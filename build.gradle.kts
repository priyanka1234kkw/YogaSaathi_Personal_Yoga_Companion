// ✅ Remove Kotlin Plugin (Since you use Java)
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false

}

buildscript {
    dependencies {
        classpath ("com.android.tools.build:gradle:8.1.1") // ✅ Keep AGP version only
    }
}

