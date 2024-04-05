plugins {
    id(Plugin.LIBRARY)
    id(Plugin.KOTLIN)
}

android {
    namespace = "com.github.fajaragungpramana.our.common"
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
        }
    }
    buildFeatures {
        viewBinding = true
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

    api(Dependency.AndroidX.APP_COMPAT)
    api(Dependency.AndroidX.CORE_KTX)
    implementation(Dependency.AndroidX.PAGING_RUNTIME_KTX)

}