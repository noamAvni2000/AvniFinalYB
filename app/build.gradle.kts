import java.util.Properties
// קריאת המפתח מ‑local.properties
val localProps = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}
val apiKey = localProps.getProperty("GOOGLE_API_KEY") ?: ""

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.avnifinalyb"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.avnifinalyb"
        minSdk = 35
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // חשיפת המפתח לקוד Java כ‑BuildConfig.GOOGLE_API_KEY
        buildConfigField("String", "GOOGLE_API_KEY", "\"$apiKey\"")
        // חלופה (ללא BuildConfig):
        // resValue("string", "google_api_key", apiKey)
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

    // ⚠️ נדרש אם משתמשים ב‑buildConfigField
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("com.google.ai.client.generativeai:generativeai:0.5.0") // SDK רשמי ל‑Gemini
    implementation("com.google.guava:guava:33.0.0-android") // Explicitly add Guava
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}