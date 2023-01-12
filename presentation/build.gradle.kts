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

    implementation("androidx.test:monitor:1.6.1")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.test:core-ktx:1.5.0")
    androidTestImplementation("junit:junit:4.13.2")

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
    implementation(Dependencies.androidX.collection)
    implementation(Dependencies.androidX.paging)

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

    implementation(Dependencies.coil.bom)
    implementation(Dependencies.coil.coil)
    implementation(Dependencies.coil.gifSupport)
    implementation(Dependencies.coil.svgSupport)

    implementation(Dependencies.timber)
}
