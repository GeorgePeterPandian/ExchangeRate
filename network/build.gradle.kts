plugins {
    `george-base-android-library-plugin`
}

android {
    namespace = "com.george.exchange.rates.network"
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures{
        buildConfig = true
    }
}

dependencies {
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
}