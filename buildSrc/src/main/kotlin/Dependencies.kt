/**
 * Created by Thanh Quang on 20/05/2022.
 */
object Dependencies {
    object module {
        const val baseUI = ":baseUI"
        const val core = ":core"
        const val utils = ":utils"
        const val domain = ":domain"
        const val data = ":data"
    }

    object kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin.core}"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin.coroutines}"
        const val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin.coroutines}"
        const val serializationJson =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlin.serializationJson}"
    }

    object androidX {
        const val activity = "androidx.activity:activity-ktx:${Versions.androidX.activity}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.androidX.appCompat}"
        const val core = "androidx.core:core-ktx:${Versions.androidX.core}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.androidX.fragment}"
        const val startUp = "androidx.startup:startup-runtime:${Versions.androidX.startUp}"
        const val annotation = "androidx.annotation:annotation:${Versions.androidX.annotations}"
        const val v4 = "androidx.legacy:legacy-support-v4:${Versions.androidX.v4}"
        const val work = "androidx.work:work-runtime-ktx:${Versions.androidX.work}"
        const val datastore =
            "androidx.datastore:datastore-preferences:${Versions.androidX.datastore}"
        const val collection = "androidx.collection:collection-ktx:${Versions.androidX.collection}"
        const val paging = "androidx.paging:paging-runtime:${Versions.androidX.paging}"

        object view {
            const val material =
                "com.google.android.material:material:${Versions.androidX.view.material}"
            const val constraintLayout =
                "androidx.constraintlayout:constraintlayout:${Versions.androidX.view.constraintLayout}"
            const val recyclerView =
                "androidx.recyclerview:recyclerview:${Versions.androidX.view.recyclerView}"
            const val recyclerViewSelection =
                "androidx.recyclerview:recyclerview-selection:${Versions.androidX.view.recyclerViewSelection}"
            const val cardView = "androidx.cardview:cardview:${Versions.androidX.view.cardView}"
        }

        object navigation {
            const val fragment =
                "androidx.navigation:navigation-fragment-ktx:${Versions.androidX.navigation}"
            const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.androidX.navigation}"
        }

        object lifecycle {
            const val viewModel =
                "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidX.lifecycle}"
            const val liveData =
                "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidX.lifecycle}"
            const val common =
                "androidx.lifecycle:lifecycle-common-java8:${Versions.androidX.lifecycle}"
            const val extensions =
                "androidx.lifecycle:lifecycle-extensions:${Versions.androidX.lifecycleExtensions}"
            const val compiler =
                "androidx.lifecycle:lifecycle-compiler:${Versions.androidX.lifecycle}"
            const val runtime =
                "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidX.lifecycle}"
        }

        object room {
            const val runtime = "androidx.room:room-runtime:${Versions.androidX.room}"
            const val ktx = "androidx.room:room-ktx:${Versions.androidX.room}"
            const val compiler = "androidx.room:room-compiler:${Versions.androidX.room}"
            const val paging = "androidx.room:room-paging:${Versions.androidX.room}"
        }
    }

    object google {
        const val gson = "com.google.code.gson:gson:${Versions.google.gson}"
        const val billing = "com.android.billingclient:billing-ktx:${Versions.google.billing}"
        const val playCore = "com.google.android.play:core:${Versions.google.playCore}"
        const val playCoreKtx = "com.google.android.play:core-ktx:${Versions.google.playCoreKtx}"
        const val playServicesAds =
            "com.google.android.gms:play-services-ads:${Versions.google.playServicesAds}"
        const val playServicesAdsIdentifier =
            "com.google.android.gms:play-services-ads-identifier:${Versions.google.playServicesAdsIdentifier}"
    }

    object koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
    }

    object retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    object glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
        const val integration = "com.github.bumptech.glide:okhttp3-integration:${Versions.glide}"
    }

    object coil {
        const val bom = "io.coil-kt:coil-bom:${Versions.coil}"
        const val coil = "io.coil-kt:coil"
        const val coilBase = "io.coil-kt:coil-base"
        const val gifSupport = "io.coil-kt:coil-gif"
        const val svgSupport = "io.coil-kt:coil-svg"
    }

    const val multidex = "androidx.multidex:multidex:${Versions.multidex}"
    const val desugarJdk = "com.android.tools:desugar_jdk_libs:${Versions.desugarJdk}"
    const val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    const val javaxApi = "javax.activation:javax.activation-api:1.2.0"
    const val viewBinding = "com.github.kirich1409:viewbindingpropertydelegate:1.4.7"
    const val leakCanaryAndroid =
        "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanaryAndroid}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val kotlinxSerializationConverter =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.kotlinxSerializationConverter}"
    const val coroutineCallAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutineCallAdapter}"
    const val flowExt = "io.github.hoc081098:FlowExt-jvm:${Versions.flowExt}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
}