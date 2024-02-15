plugins {
    id(Plugins.kotlinMultiPlatform)
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":domain"))
                implementation(project(":utils"))
            }
        }
    }
}
