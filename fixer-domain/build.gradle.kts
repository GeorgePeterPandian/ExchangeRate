plugins {
    `george-base-android-library-plugin`
}

android {
    namespace = "com.george.exchange.rates.domain"

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(project(":core"))

    //Inject
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.core)

    //Test
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
}