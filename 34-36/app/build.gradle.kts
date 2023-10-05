plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.sdv.kit.application"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sdv.kit.application"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    kotlin {
        jvmToolchain(17)
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    val splashScreenVersion = "1.1.0-alpha02"
    val retrofitGsonVersion = "2.9.0"
    val glideVersion = "4.16.0"
    val roomVersion = "2.5.2"
    val viewPager2Version = "1.1.0-beta02"

    // ViewPager2
    implementation("androidx.viewpager2:viewpager2:$viewPager2Version")

    // Splash Screen
    implementation("androidx.core:core-splashscreen:$splashScreenVersion")

    // Retrofit2 + Gson
    implementation("com.squareup.retrofit2:converter-gson:$retrofitGsonVersion")

    // Glide
    implementation("com.github.bumptech.glide:glide:$glideVersion")

    // Room
    ksp("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}