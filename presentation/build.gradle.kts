plugins {
    `base-app-plugin`
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
}

baseApp {
    viewBinding = true
    parcelize = true
    kotlinxSerialization = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

//    implementation(project(deps.module.domain))
//    implementation(project(deps.module.data))

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

//    implementation(deps.androidX.room.runtime)
//    kapt(deps.androidX.room.compiler)
//    implementation(deps.androidX.room.ktx)

    implementation(Dependencies.koin.core)
    implementation(Dependencies.koin.android)

//    implementation(deps.retrofit.retrofit)

//    implementation(deps.glide.glide)
//    kapt(deps.glide.compiler)
//    implementation(deps.glide.integration) { exclude(group = "glide-parent") }

//    implementation(deps.multidex)
//    implementation(deps.okhttp3)
//    implementation(deps.loggingInterceptor)
//    implementation(deps.javaxApi)
//    implementation(deps.timber)
//    implementation(deps.kotlinxSerializationConverter)
}
