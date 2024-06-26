object Dependency {

    object AndroidX {
        const val APP_COMPAT = "androidx.appcompat:appcompat:1.6.1"
        const val CORE_KTX = "androidx.core:core-ktx:1.12.0"
        const val SPLASH_SCREEN = "androidx.core:core-splashscreen:1.0.1"
        const val ACTIVITY_KTX = "androidx.activity:activity-ktx:1.8.2"
        const val NAVIGATION_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:${Version.NAVIGATION}"
        const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${Version.NAVIGATION}"
        const val PAGING_RUNTIME_KTX = "androidx.paging:paging-runtime-ktx:3.2.1"
        const val ROOM_RUNTIME = "androidx.room:room-ktx:2.6.1"
        const val ROOM_COMPILER = "androidx.room:room-compiler:2.6.1"
        const val DATA_STORE = "androidx.datastore:datastore-preferences:1.0.0"

        const val TEST_CORE_KTX = "androidx.test:core-ktx:1.5.0"
        const val RUNNER = "androidx.test:runner:1.5.2"
        const val RULES = "androidx.test:rules:1.5.0"

        const val CORE_TESTING = "androidx.arch.core:core-testing:2.2.0"
    }

    object Common {
        const val COIL = "io.coil-kt:coil:2.5.0"
        const val LOTTIE = "com.airbnb.android:lottie:6.3.0"
        const val SHIMMER = "com.facebook.shimmer:shimmer:0.5.0"
        const val EVENT_BUS = "org.greenrobot:eventbus:3.3.1"
        const val PROCESS_PHOENIX = "com.jakewharton:process-phoenix:2.2.0"
    }

    object Google {
        const val MATERIAL = "com.google.android.material:material:1.11.0"
        const val HILT = "com.google.dagger:hilt-android:${Version.HILT}"
        const val HILT_COMPILER = "com.google.dagger:hilt-compiler:${Version.HILT}"
    }

    object JetBrains {
        const val COROUTINE_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0"
    }

    object Mockito {
        const val MOCKITO_CORE = "org.mockito:mockito-core:5.10.0"
        const val MOCKITO_INLINE = "org.mockito:mockito-inline:5.2.0"
    }

    object Square {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:2.9.0"
        const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:2.9.0"
        const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:4.12.0"
    }

}