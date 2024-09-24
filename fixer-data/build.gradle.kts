plugins {
    `george-base-android-library-plugin`
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization")
    kotlin("kapt")
}

android {
    namespace = "com.george.exchange.rates.data"
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        buildConfig = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(project(":core"))
    implementation(project(":network"))
    implementation(project(":fixer-domain"))

    //Hilt
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)

    implementation(libs.gson)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)

    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.retrofit2.kotlinx.serialization.converter)


    //Test
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockito.core)

    androidTestImplementation(libs.junit)

    androidTestImplementation(libs.androidx.core.common)
    androidTestImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.room.testing)
}