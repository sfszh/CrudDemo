import co.ruizhang.buildsrc.Libs

plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.5.10"
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "co.ruizhang.cruddemo"
        minSdk = 26
        targetSdk = 30
        versionCode = 2
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libs.AndroidX.Compose.version
    }
}

dependencies {

    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.Coroutines.android)

    implementation(Libs.Koin.core)
    implementation(Libs.Koin.android)
    implementation(Libs.Koin.compose)


    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.navigation)
    implementation(Libs.AndroidX.Activity.activityCompose)
    implementation(Libs.AndroidX.ConstraintLayout.constraintLayoutCompose)

    //region compose
    implementation(Libs.AndroidX.Compose.runtime)
    implementation(Libs.AndroidX.Compose.foundation)
    implementation(Libs.AndroidX.Compose.layout)
    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.uiUtil)
    implementation(Libs.AndroidX.Compose.viewModel)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.animation)
    implementation(Libs.AndroidX.Compose.iconsExtended)
    implementation(Libs.AndroidX.Compose.tooling)
    //endregion

    implementation(Libs.AndroidX.Lifecycle.runTime)
    implementation(Libs.AndroidX.Lifecycle.runTimeKtx)
    implementation(Libs.AndroidX.Lifecycle.viewModelKtx)
    implementation(Libs.AndroidX.Lifecycle.extensions)
    implementation(Libs.AndroidX.DataStore.preferences)

    implementation(Libs.Accompanist.coil)
    implementation(Libs.Accompanist.insets)


    implementation(Libs.Kotlinx.serializationCore)

    with(Libs.Ktor) {
        implementation(clientCore)
        implementation(clientAndroid)
        implementation(clientJson)
        implementation(clientLogging)
        implementation(clientSerialization)
    }

    //region room
    implementation(Libs.Room.runtime)
    annotationProcessor(Libs.Room.compiler)
    kapt(Libs.Room.compiler)
    implementation(Libs.Room.ktx)
    //endregion

    //region Test
    testImplementation(Libs.JUnit.junit)
    testImplementation(Libs.MockK.mockk)
    testImplementation(Libs.Coroutines.test)
    testImplementation(Libs.Arch.testing)
    testImplementation(Libs.Koin.test)
    testImplementation(Libs.Koin.junit4)

    androidTestImplementation(Libs.AndroidX.Activity.activityCompose)
    androidTestImplementation(Libs.AndroidX.Test.core)
    androidTestImplementation(Libs.AndroidX.Test.espressoCore)
    androidTestImplementation(Libs.AndroidX.Test.rules)
    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Compose.uiTest)
    androidTestImplementation(Libs.Room.testing)
    //endregion
}