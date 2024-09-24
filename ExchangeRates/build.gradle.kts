plugins {
    `george-base-android-application-plugin`
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.george.exchange.rates.task"

    defaultConfig {
        applicationId = "com.george.exchange.rates.task"
        vectorDrawables.useSupportLibrary = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures{
        buildConfig = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(project(":core"))
    implementation(project(":fixer-domain"))
    implementation(project(":fixer-data"))

    //Hilt
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)

    //Core
    implementation(platform(libs.kotlin.bom))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    //Flags
    implementation(libs.flagkit.android)

    //UI
    implementation(libs.ui)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //Google Material
    implementation(libs.material)

    //Test
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
}