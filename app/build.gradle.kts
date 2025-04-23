import java.util.Properties
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)

}

android {
    namespace = "com.example.yoga"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.yoga"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Load API key from local.properties
        val localProperties = Properties().apply {
            load(project.rootProject.file("local.properties").inputStream())
        }
        buildConfigField(
            "String",
            "HF_API_KEY",
            "\"${localProperties.getProperty("HF_API_KEY")}\""
        )

        buildConfigField("String", "NEWS_API_KEY", "\"${localProperties.getProperty("NEWS_API_KEY")}\"")

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        buildConfig = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
dependencies {
    // Existing dependencies
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.gson)
    implementation(libs.androidx.cardview)
    implementation(libs.lottie)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.shimmer)
    implementation(libs.androidx.core.ktx)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    implementation(libs.glide)
    implementation(libs.viewpager2)

    //Updated Firebase dependencies
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)

    //Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}