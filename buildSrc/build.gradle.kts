plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("george-base-android-application-plugin") {
            id = "george-base-android-application-plugin"
            implementationClass =
                "com.build.george.dependency.plugin.BaseAndroidApplicationPlugin"
        }
        register("george-base-android-library-plugin") {
            id = "george-base-android-library-plugin"
            implementationClass = "com.build.george.dependency.plugin.BaseAndroidLibraryPlugin"
        }
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:8.6.1")
    implementation("com.android.tools.build:gradle-api:8.6.1")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.52")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
    implementation("org.jetbrains.kotlin:kotlin-serialization:1.9.10")
}