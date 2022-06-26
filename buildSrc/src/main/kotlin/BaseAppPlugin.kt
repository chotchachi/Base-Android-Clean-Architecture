import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.JavaVersion.VERSION_1_8
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Created by Thanh Quang on 20/05/2022.
 */
private inline val Project.libraryExtension get() = extensions.getByType<LibraryExtension>()
private inline val Project.appExtension get() = extensions.getByType<AppExtension>()
private inline val Project.javaPluginExtension get() = extensions.getByType<JavaPluginExtension>()

open class BaseAppExtension {
    var viewBinding: Boolean = false
    var parcelize: Boolean = false
    var kotlinxSerialization: Boolean = false
}

class BaseAppPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.run {
            plugins.all {
                when (this) {
                    is JavaPlugin, is JavaLibraryPlugin -> {
                        javaPluginExtension.run {
                            targetCompatibility = VERSION_1_8
                            sourceCompatibility = VERSION_1_8
                        }
                    }
                    is LibraryPlugin -> {
                        plugins.apply("kotlin-android")
                        configAndroidLibrary()
                    }
                    is AppPlugin -> {
                        plugins.apply("kotlin-android")
                        configAndroidApplication()
                    }
                    is KotlinBasePluginWrapper -> configKotlinOptions()
                }
            }
        }

        val baseAppExtension =
            project.extensions.create("baseApp", BaseAppExtension::class)

        project.afterEvaluate {
            project.plugins.all {
                when (this) {
                    is LibraryPlugin -> {
                        libraryExtension.buildFeatures {
                            viewBinding = baseAppExtension.viewBinding
                        }
                        enableParcelize(baseAppExtension.parcelize)
                        enableKotlinxSerialization(baseAppExtension.kotlinxSerialization)
                    }
                    is AppPlugin -> {
                        appExtension.buildFeatures.run {
                            viewBinding = baseAppExtension.viewBinding
                        }
                        enableParcelize(baseAppExtension.parcelize)
                        enableKotlinxSerialization(baseAppExtension.kotlinxSerialization)
                    }
                }
            }
        }
    }
}

private fun Project.configAndroidLibrary() = libraryExtension.run {
    buildToolsVersion = Versions.sdk.buildTools
    compileSdk = Versions.sdk.compile

    defaultConfig {
        minSdk = Versions.sdk.min
        targetSdk = Versions.sdk.target

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = VERSION_1_8
        targetCompatibility = VERSION_1_8
    }

    lint {
        isCheckReleaseBuilds = false
    }
}

private fun Project.configAndroidApplication() = appExtension.run {
    buildToolsVersion(Versions.sdk.buildTools)
    compileSdkVersion(Versions.sdk.compile)

    defaultConfig {
        applicationId = AppConfig.applicationId

        minSdk = Versions.sdk.min
        targetSdk = Versions.sdk.target

        multiDexEnabled = AppConfig.multiDexEnabled
        vectorDrawables.useSupportLibrary = AppConfig.useSupportLibrary
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        addResourceConfigurations(AppConfig.supportedLocales)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false

            manifestPlaceholders(
                mapOf(
                    Pair("crashlyticsCollectionEnabled", true)
                )
            )

            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isDebuggable = true

            manifestPlaceholders(
                mapOf(
                    Pair("crashlyticsCollectionEnabled", false)
                )
            )
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = VERSION_1_8
        targetCompatibility = VERSION_1_8
    }

    lintOptions {
        isCheckReleaseBuilds = false
    }
}

private fun Project.enableParcelize(enabled: Boolean) {
    if (enabled) {
        plugins.apply("kotlin-parcelize")
    }
}

private fun Project.enableKotlinxSerialization(enable: Boolean) {
    if (enable) {
        plugins.apply("kotlinx-serialization")
    }
}

private fun Project.configKotlinOptions() {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = VERSION_1_8.toString()
            freeCompilerArgs = freeCompilerArgs + listOf("-Xopt-in=kotlin.RequiresOptIn")
        }
    }
}