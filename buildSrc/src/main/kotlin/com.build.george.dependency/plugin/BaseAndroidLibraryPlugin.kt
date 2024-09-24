package com.build.george.dependency.plugin

import com.android.build.gradle.LibraryExtension
import com.build.george.dependency.versioning.BackEndURL
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.Properties

abstract class BaseAndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {

        target.configureAndroidLibraryPipeline()

        val android = target.extensions.getByType(LibraryExtension::class.java)

        android.apply {

            compileSdk = 34

            defaultConfig.apply {
                minSdk = 23
                targetSdk = 34
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                vectorDrawables {
                    useSupportLibrary = true
                }
                consumerProguardFiles("consumer-rules.pro")
                val localProperties = Properties().apply {
                    load(target.rootProject.file("local.properties").inputStream())
                }
                buildConfigField(
                    "String", "ACCESS_KEY", localProperties.getProperty("ACCESS_KEY")
                )
            }


            //Source Sets
            with(sourceSets) {
                getByName("main").java.srcDirs("src/main/kotlin/")
                getByName("test").java.srcDirs("src/test/kotlin/")
                getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
            }


            //Compile options
            compileOptions.apply {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            buildFeatures {
                buildConfig = true
                dataBinding = true
            }

            packagingOptions.apply {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    merges += "META-INF/LICENSE.md"
                    merges += "META-INF/LICENSE-notice.md"
                }
            }

            testOptions {
                unitTests.isReturnDefaultValues = true
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