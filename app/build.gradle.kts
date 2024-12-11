plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.gym"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.gym"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    viewBinding {
        enable = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.gridlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation ("androidx.core:core-ktx:1.0.2")
    implementation ("androidx.fragment:fragment-ktx:1.0.0")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.0.0")

    implementation ("com.google.android.material:material:1.3.0-alpha03")
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    /*
    *https://github.com/square/picasso
    * *  */

    implementation("com.squareup.picasso:picasso:2.8")

    /*
    * https://developer.android.com/jetpack/androidx/releases/recyclerview?hl=es-419
    * */
    implementation("androidx.recyclerview:recyclerview:1.3.2")
}