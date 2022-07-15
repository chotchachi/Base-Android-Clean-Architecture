plugins {
    `base-app-plugin`
    `android-library`
    `kotlin-kapt`
}

baseApp {
    kotlinxSerialization = true
}

dependencies {
    implementation(project(Dependencies.module.core))
    implementation(project(Dependencies.module.domain))
    implementation(project(Dependencies.module.utils))

    implementation(Dependencies.kotlin.stdlib)
    implementation(Dependencies.kotlin.coroutinesCore)
    implementation(Dependencies.kotlin.coroutinesAndroid)
    implementation(Dependencies.kotlin.serializationJson)

    implementation(Dependencies.koin.core)

    implementation(Dependencies.retrofit.retrofit)

    implementation(Dependencies.androidX.room.runtime)
    kapt(Dependencies.androidX.room.compiler)
    implementation(Dependencies.androidX.room.ktx)

    implementation(Dependencies.androidX.datastore)

    implementation(Dependencies.loggingInterceptor)
    implementation(Dependencies.kotlinxSerializationConverter)

    implementation(Dependencies.timber)
}
