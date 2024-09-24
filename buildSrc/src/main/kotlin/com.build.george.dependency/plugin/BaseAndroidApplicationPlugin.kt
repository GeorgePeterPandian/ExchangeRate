package com.build.george.dependency.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.build.george.dependency.versioning.BackEndURL
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class BaseAndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        target.configureAndroidApplicationPipeline()

        val android = target.extensions.getByType(ApplicationExtension::class.java)

        with(android) {
            compileSdk = 34

            defaultConfig.apply {
                minSdk = 23
                targetSdk = 34
                versionCode = 1
                versionName = "1.0"
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                vectorDrawables {
                    useSupportLibrary = true
                }

            }

            compileOptions.apply {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            buildFeatures {
                viewBinding = true
                dataBinding = true
                compose = true
            }

            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    merges += "META-INF/LICENSE.md"
                    merges += "META-INF/LICENSE-notice.md"
                }
            }

            //Build types
            buildTypes {
                release {
                    isMinifyEnabled = true
                    enableUnitTestCoverage = false
                    enableAndroidTestCoverage = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
                debug {
                    isMinifyEnabled = false
                    enableUnitTestCoverage = true
                    enableAndroidTestCoverage = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }

            //Flavors
            flavorDimensions.add("environment")
            productFlavors {
                create("dev").apply {
                    dimension = "environment"
                    buildConfigField(
                        "String", "REST_API", addQuotes(BackEndURL.Dev.RESTAPI)
                    )
                }
                create("stage").apply {
                    dimension = "environment"
                    buildConfigField(
                        "String", "REST_API", addQuotes(BackEndURL.Stage.RESTAPI)
                    )
                }
                create("prod").apply {
                    dimension = "environment"
                    buildConfigField(
                        "String", "REST_API", addQuotes(BackEndURL.Prod.RESTAPI)
                    )
                }
            }
        }
    }
}