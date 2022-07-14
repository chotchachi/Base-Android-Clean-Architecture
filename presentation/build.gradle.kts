import io.github.reactivecircus.appversioning.toSemVer

plugins {
    `base-app-plugin`
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
    id("io.github.reactivecircus.app-versioning") version "1.1.2"
}

baseApp {
    viewBinding = true
    parcelize = true
    kotlinxSerialization = true
}

appVersioning {
    overrideVersionCode { gitTag, _, _ ->
        val semVer = gitTag.toSemVer()
        semVer.major * 1000000 + semVer.minor * 1000 + semVer.patch
    }

    overrideVersionName { gitTag, _, _ ->
        gitTag.rawTagName
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Dependencies.module.baseUI))
    implementation(project(Dependencies.module.core))
    implementation(project(Dependencies.module.utils))
    implementation(project(Dependencies.module.domain))
    implementation(project(Dependencies.module.data))

    coreLibraryDesugaring(Dependencies.desugarJdk)

    implementation(Dependencies.kotlin.stdlib)
    implementation(Dependencies.kotlin.coroutinesCore)
    implementation(Dependencies.kotlin.coroutinesAndroid)
    implementation(Dependencies.kotlin.serializationJson)

    implementation(Dependencies.androidX.annotation)
    implementation(Dependencies.androidX.v4)
    implementation(Dependencies.androidX.activity)
    implementation(Dependencies.androidX.appCompat)
    implementation(Dependencies.androidX.core)
    implementation(Dependencies.androidX.fragment)
    implementation(Dependencies.androidX.startUp)
    implementation(Dependencies.androidX.work)

    implementation(Dependencies.androidX.view.material)
    implementation(Dependencies.androidX.view.constraintLayout)
    implementation(Dependencies.androidX.view.recyclerView)
    implementation(Dependencies.androidX.view.recyclerViewSelection)
    implementation(Dependencies.androidX.view.cardView)

    implementation(Dependencies.androidX.navigation.fragment)
    implementation(Dependencies.androidX.navigation.ui)

    implementation(Dependencies.androidX.lifecycle.viewModel)
    implementation(Dependencies.androidX.lifecycle.runtime)
    implementation(Dependencies.androidX.lifecycle.liveData)
    implementation(Dependencies.androidX.lifecycle.extensions)
    implementation(Dependencies.androidX.lifecycle.common)
    kapt(Dependencies.androidX.lifecycle.compiler)

    implementation(Dependencies.koin.core)
    implementation(Dependencies.koin.android)

//    implementation(Dependencies.androidX.room.runtime)
//    kapt(Dependencies.androidX.room.compiler)
//    implementation(Dependencies.androidX.room.ktx)

//    implementation(Dependencies.retrofit.retrofit)

//    implementation(Dependencies.glide.glide)
//    kapt(Dependencies.glide.compiler)
//    implementation(Dependencies.glide.integration) { exclude(group = "glide-parent") }

//    implementation(Dependencies.multidex)
//    implementation(Dependencies.okhttp3)
//    implementation(Dependencies.loggingInterceptor)
//    implementation(Dependencies.javaxApi)
//    implementation(Dependencies.timber)
//    implementation(Dependencies.kotlinxSerializationConverter)
}
