plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" // Depends on your kotlin version
    id("org.jetbrains.kotlin.plugin.serialization")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.gladed.androidgitversion") version "0.4.14"

}

/**
 * Check if a signing.gradle file exists and import it
 */
if (file(rootProject.projectDir.absolutePath + "/signing.gradle").exists()) {
    apply(rootProject.projectDir.absolutePath + "/signing.gradle")
}

androidGitVersion {
    codeFormat = "MNNPPPBBB"
    format = "%tag%%.count%%-dirty%"
    format = if (System.getenv("CI") == null) {
        "%tag%%.count%%-dirty%"
    } else {
        //if it's a CI build ignore dirty because bundle execute demands bundler/gems to be up to date (forces it server-side) --> gemlock always changed
        "%tag%%.count%"
    }
    //when their are uncommitted changes add dirty to version name
    untrackedIsDirty = true
}

android {
    namespace = "be.cbconnectit.portfolio.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "be.cbconnectit.portfolio.app"
        minSdk = 28
        targetSdk = 34
        versionName = androidGitVersion.name()
        versionCode = androidGitVersion.code()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
//            signingConfig = signingConfigs.getByName("release")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
//            signingConfig = signingConfigs.getByName("release")
            signingConfig = signingConfigs.getByName("debug") //TODO: should be removed and replaced by a proper signingConfig!!
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
        viewBinding = true
        dataBinding = true
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    flavorDimensions += "env"

    productFlavors {
        create("dev") {
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            dimension = "env"

            resValue("string","app_name","CB connect IT Portfolio Dev")
            buildConfigField("String", "API_BASE_URL", "\"https://www.cb-connect-it.com/api/v1/\"")
        }

        create("qa") {
            applicationIdSuffix = ".test"
            versionNameSuffix = "-test"
            dimension = "env"

            resValue("string","app_name","CB connect IT Portfolio Test")
            buildConfigField("String", "API_BASE_URL", "\"https://www.cb-connect-it.com/api/v1/\"")
        }

        create("staging") {
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"
            dimension = "env"

            resValue("string","app_name","CB connect IT Portfolio Staging")
            buildConfigField("String", "API_BASE_URL", "\"https://www.cb-connect-it.com/api/v1/\"")
        }

        create("production") {
            dimension = "env"

            resValue("string","app_name","CB connect IT Portfolio")
            buildConfigField("String", "API_BASE_URL", "\"https://www.cb-connect-it.com/api/v1/\"")
        }
    }
}

dependencies {

    implementation(project(mapOf("path" to ":dataRow")))

    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0-beta01")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.3")
    implementation("androidx.databinding:databinding-runtime:8.1.1")
//    implementation("androidx.datastore:datastore-core:1.0.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Compose
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Compose (testing)
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.05.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Image Library for Compose
    implementation("io.coil-kt:coil-compose:2.3.0")
    implementation("io.coil-kt:coil-svg:2.3.0")

    // Compose Nav Destinations
    implementation("io.github.raamcosta.compose-destinations:animations-core:1.9.54")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.9.54")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // CustomTabs
    implementation("androidx.browser:browser:1.6.0")

    // Koin
    implementation("io.insert-koin:koin-android:3.4.0")
    implementation("io.insert-koin:koin-androidx-navigation:3.4.0")
    testImplementation("io.insert-koin:koin-test-junit4:3.4.0")
    implementation("io.insert-koin:koin-androidx-compose:3.4.3")

    // Ktor
    implementation("io.ktor:ktor-client-core:2.3.5")
    implementation("io.ktor:ktor-client-android:2.3.5")
    implementation("io.ktor:ktor-client-logging:2.3.5")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.5")
    implementation("io.ktor:ktor-client-serialization:2.3.5")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // Room Database
    implementation("androidx.room:room-runtime:2.6.0")
    ksp("androidx.room:room-compiler:2.6.0")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.6.0")

    implementation("com.github.deano2390:FlowTextView:2.0.5")

    // Firebase stuff
    implementation(platform("com.google.firebase:firebase-bom:32.4.0"))
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")

    implementation("com.github.jeziellago:compose-markdown:0.5.0")
    implementation("com.github.GIGAMOLE:ComposeCollapsingToolbar:1.0.8")
}