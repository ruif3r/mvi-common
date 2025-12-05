plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.ruif3r.mvicommon"
    compileSdk = 36

    defaultConfig {
        minSdk = 23

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

    testOptions {
        targetSdk = 36
    }

    lint {
        targetSdk = 36
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    api(platform(libs.androidx.compose.bom))

    // Use 'api' for dependencies that are part of the mvi-common's public API.
    // The consuming app can override these versions by declaring a newer version.
    api(libs.androidx.core.ktx)
    api(libs.androidx.fragment.ktx)
    api(libs.androidx.appcompat)
    api(libs.androidx.activity.compose)
    api(libs.androidx.compose.ui)

    // Test dependencies remain as they are
    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.test.ext.junit)
    androidTestImplementation (libs.androidx.espresso.core)
}
