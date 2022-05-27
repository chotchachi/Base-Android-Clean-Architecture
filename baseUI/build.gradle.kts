plugins {
    `base-app-plugin`
    `android-library`
}

baseApp {
    viewBinding = true
}

dependencies {
    implementation(Dependencies.kotlin.stdlib)

    implementation(Dependencies.androidX.annotation)
    implementation(Dependencies.androidX.v4)
    implementation(Dependencies.androidX.activity)
    implementation(Dependencies.androidX.appCompat)
    implementation(Dependencies.androidX.core)
    implementation(Dependencies.androidX.fragment)
    implementation(Dependencies.androidX.view.material)

    implementation(Dependencies.timber)
}
