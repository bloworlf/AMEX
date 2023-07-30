plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // Hilt plugins required
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "io.drdroid.amex"
    compileSdk = 34

    defaultConfig {
        applicationId = "io.drdroid.amex"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.material:material-icons-extended:1.6.0-alpha02")
    implementation("androidx.compose.ui:ui-graphics:1.4.3")
    implementation("androidx.compose.runtime:runtime-livedata:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.compose.material3:material3:1.1.1")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //navigation
    implementation("androidx.navigation:navigation-compose:2.6.0")

    //hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-compiler:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.3")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.3")

    //test coroutine
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

    //mockito
    testImplementation("org.mockito:mockito-core:3.12.4")
//    androidTestImplementation 'org.mockito:mockito-android:2.2.0'
    testImplementation("org.mockito:mockito-inline:3.11.2")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
}