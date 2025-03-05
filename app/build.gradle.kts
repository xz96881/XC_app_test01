plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.zxz.xc_test01"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.zxz.xc_test01"
        minSdk = 23
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}


dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // 手动添加以下依赖（覆盖自动生成的版本）
    implementation("androidx.core:core-ktx:1.12.0")  // 解决Bundle错误
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0") // 图表库
    implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5") // MQTT核心
    implementation("org.eclipse.paho:org.eclipse.paho.android.service:1.1.1") // MQTT安卓服务
    implementation("androidx.media3:media3-exoplayer:1.2.0") // 视频解码库
}