plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.gradle.kotlin)
    implementation(libs.gradle.android)
    implementation(libs.gradle.hilt)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "com.wsr.application"
            implementationClass = "plugins.AndroidApplicationPlugin"
        }
        register("androidCompose") {
            id = "com.wsr.compose"
            implementationClass = "plugins.AndroidComposePlugin"
        }
        register("androidLibrary") {
            id = "com.wsr.library"
            implementationClass = "plugins.AndroidLibraryPlugin"
        }
        register("daggerHilt") {
            id = "com.wsr.dagger-hilt"
            implementationClass = "plugins.DaggerHiltPlugin"
        }
        register("ktlint") {
            id = "com.wsr.ktlint"
            implementationClass = "plugins.KtlintPlugin"
        }
    }
}
