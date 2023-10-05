plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.sdv.kit.taskard"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sdv.kit.taskard"
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
    viewBinding {
        enable = true
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("com.google.firebase:firebase-database-ktx:20.2.2")
    val navVersion = "2.7.3"
    val splashScreenVersion = "1.1.0-alpha02"
    val gpsAuthVersion = "20.7.0"
    val roomVersion = "2.5.2"
    val rxAndroidVersion = "3.0.2"
    val rxJavaVersion = "3.1.7"
    val viewPagerVersion = "1.0.0"
    val glideVersion = "4.16.0"

    // Glide
    implementation("com.github.bumptech.glide:glide:$glideVersion")

    // ViewPager2
    implementation("androidx.viewpager2:viewpager2:$viewPagerVersion")

    // RxJava for Android
    implementation("io.reactivex.rxjava3:rxandroid:$rxAndroidVersion")
    implementation("io.reactivex.rxjava3:rxjava:$rxJavaVersion")

    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    // RxJava3 support
    implementation("androidx.room:room-rxjava3:$roomVersion")

    // Google Play Services Auth (Sign in with Google)
    implementation("com.google.android.gms:play-services-auth:$gpsAuthVersion")

    // Jetpack Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    // Splash Screen
    implementation("androidx.core:core-splashscreen:$splashScreenVersion")

    // Core
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}