plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.gradle.kotlin)
}

gradlePlugin {
    plugins {
        register("kotlinMultiPlatform") {
            id = "com.wsr.multiplatform"
            implementationClass = "plugins.KotlinMultiPlatformPlugin"
        }
        register("ktlint") {
            id = "com.wsr.ktlint"
            implementationClass = "plugins.KtlintPlugin"
        }
    }
}
