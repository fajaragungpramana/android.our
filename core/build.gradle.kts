plugins {
    id(Plugin.LIBRARY)
    id(Plugin.KOTLIN)
    id(Plugin.KOTLIN_KAPT)
    id(Plugin.HILT)
}

android {
    namespace = "com.github.fajaragungpramana.our.core"
    compileSdk = Version.TARGET_SDK

    defaultConfig {
        minSdk = Version.MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", Config.API_BASE_URL, "\"${Config.API_BASE_URL_VALUE}\"")
        }
        debug {
            buildConfigField("String", Config.API_BASE_URL, "\"${Config.API_BASE_URL_VALUE}\"")
        }
    }
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(Dependency.Google.HILT)
    kapt(Dependency.Google.HILT_COMPILER)

    api(Dependency.Square.RETROFIT)
    implementation(Dependency.Square.CONVERTER_GSON)
    implementation(Dependency.Square.LOGGING_INTERCEPTOR)

    testApi(Dependency.AndroidX.CORE_TESTING)
    testApi(Dependency.Mockito.MOCKITO_CORE)
    testApi(Dependency.Mockito.MOCKITO_INLINE)
    testApi(Dependency.JetBrains.COROUTINE_TEST)

}

kapt {
    correctErrorTypes = true
}