plugins {
    `base-app-plugin`
    `android-library`
    `kotlin-kapt`
}

baseApp {

}

dependencies {
    implementation(Dependencies.kotlin.stdlib)
    implementation(Dependencies.kotlin.coroutinesCore)
    implementation(Dependencies.kotlin.coroutinesAndroid)

    implementation(Dependencies.androidX.activity)
    implementation(Dependencies.androidX.appCompat)
    implementation(Dependencies.androidX.core)
    implementation(Dependencies.androidX.fragment)

    implementation(Dependencies.androidX.navigation.fragment)
    implementation(Dependencies.androidX.navigation.ui)

    implementation(Dependencies.androidX.lifecycle.viewModel)
    implementation(Dependencies.androidX.lifecycle.runtime)
    implementation(Dependencies.androidX.lifecycle.liveData)
    implementation(Dependencies.androidX.lifecycle.extensions)
    implementation(Dependencies.androidX.lifecycle.common)
    kapt(Dependencies.androidX.lifecycle.compiler)

    implementation(Dependencies.timber)
    implementation(Dependencies.okhttp3)
}
