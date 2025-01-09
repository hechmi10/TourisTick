plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace="tn.esprit.touristick"
    compileSdk=35

    defaultConfig {
        applicationId="tn.esprit.touristick"
        minSdk=25
        targetSdk=34
        versionCode=1
        versionName="1.0"
        testInstrumentationRunner="androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug{
            buildConfigField("String","geminiApiKey","\"AIzaSyAAPnJWRl2bqJmm_tFAEhdakn-zwMn1U4I\"")
        }
        release {
            buildConfigField("String","geminiApiKey","\"AIzaSyAAPnJWRl2bqJmm_tFAEhdakn-zwMn1U4I\"")
            isMinifyEnabled=true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt") ,
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility=JavaVersion.VERSION_1_8
        targetCompatibility=JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget="1.8"
    }
    viewBinding {
        enable=true
    }
    packaging {
        resources {
            excludes+="META-INF/versions/9/OSGI-INF/MANIFEST.MF"
        }
    }
    buildFeatures{
        buildConfig=true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.gridlayout)
    implementation(libs.firebase.firestore.ktx)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation("com.google.firebase:firebase-auth")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation(libs.identity.jvm)
    implementation(libs.firebase.vertexai)
    implementation(libs.generativeai)
    implementation(libs.firebase.appcheck.interop)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}

