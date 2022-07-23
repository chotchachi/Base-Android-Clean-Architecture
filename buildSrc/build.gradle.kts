repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
    maven(url = "https://plugins.gradle.org/m2/")
}

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

gradlePlugin {
    plugins {
        register("base-app-plugin") {
            id = "base-app-plugin"
            implementationClass = "BaseAppPlugin"
        }
    }
}

internal object PluginVersions {
    const val kotlin = "1.6.21"
    const val androidGradle = "7.0.3"
    const val navigationSafeArgs = "2.5.0"
    const val googleServices = "4.3.10"
    const val crashlytics = "2.7.1"
    const val spotless = "5.17.0"
    const val gradleVersions = "0.42.0"
}

internal object Plugins {
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersions.kotlin}"
    const val androidGradle = "com.android.tools.build:gradle:${PluginVersions.androidGradle}"
    const val navigationSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${PluginVersions.navigationSafeArgs}"
    const val googleServices = "com.google.gms:google-services:${PluginVersions.googleServices}"
    const val crashlytics =
        "com.google.firebase:firebase-crashlytics-gradle:${PluginVersions.crashlytics}"
    const val spotless = "com.diffplug.spotless:spotless-plugin-gradle:${PluginVersions.spotless}"
    const val gradleVersionsPlugin =
        "com.github.ben-manes:gradle-versions-plugin:${PluginVersions.gradleVersions}"
    const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${PluginVersions.kotlin}"
}

dependencies {
    implementation(Plugins.kotlin)
    implementation(Plugins.androidGradle)
    implementation(Plugins.navigationSafeArgs)
    implementation(Plugins.googleServices)
    implementation(Plugins.crashlytics)
    implementation(Plugins.spotless)
    implementation(Plugins.gradleVersionsPlugin)
    implementation(Plugins.kotlinSerialization)
}