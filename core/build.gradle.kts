plugins {
    `george-base-android-library-plugin`
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.george.exchange.rates.core"

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    //Inject
    implementation(libs.javax.inject)

    //Coroutines
    implementation(libs.kotlinx.coroutines.core)

    //Test
    implementation(libs.junit)
    implementation(libs.kotlinx.coroutines.test)

    //Hilt
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)
}