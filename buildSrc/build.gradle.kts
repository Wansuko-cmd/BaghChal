plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.gradle.kotlin)
}

gradlePlugin {
    plugins {
        register("ktlint") {
            id = "com.wsr.ktlint"
            implementationClass = "plugins.KtlintPlugin"
        }
    }
}
