package com.build.george.dependency.plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

/**
 * Gradle plugin implemented by [BaseAndroidApplicationPlugin].
 */
inline val PluginDependenciesSpec.`george-base-android-application-plugin`: PluginDependencySpec
    get() = id("george-base-android-application-plugin")

/**
 * Gradle plugin implemented by [BaseAndroidLibraryPlugin].
 */
inline val PluginDependenciesSpec.`george-base-android-library-plugin`: PluginDependencySpec
    get() = id("george-base-android-library-plugin")


internal fun Project.configureAndroidApplicationPipeline() = run {
    apply(plugin = "com.android.application")
    apply(plugin = "org.jetbrains.kotlin.android")
}

internal fun Project.configureAndroidLibraryPipeline() = run {
    apply(plugin = "com.android.library")
    apply(plugin = "org.jetbrains.kotlin.android")
}

internal fun addQuotes(text: String) = "\"" + text + "\""