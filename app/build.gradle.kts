@file:Suppress("UnstableApiUsage")

plugins {
    id(Plugins.androidApplication)
    id(Plugins.daggerHilt)
}

android {
    namespace = "com.wsr"

    defaultConfig {
        applicationId = "com.wsr"
        versionCode = 1
        versionName = "0.01"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles.add(getDefaultProguardFile("proguard-android-optimize.txt"))
            proguardFiles.add(file("proguard-rules.pro"))
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":ui"))
    implementation(project(":utils"))

    implementation(libs.bundles.androidx)

    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.androidx.test)
}
