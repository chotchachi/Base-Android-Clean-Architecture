plugins {
    `base-app-plugin`
    `android-library`
}

baseApp {
    parcelize = true
    kotlinxSerialization = true
}

dependencies {
    implementation(Dependencies.kotlin.stdlib)
    implementation(Dependencies.kotlin.coroutinesCore)
    implementation(Dependencies.kotlin.coroutinesAndroid)
    implementation(Dependencies.kotlin.serializationJson)

    implementation(Dependencies.androidX.annotation)
}
