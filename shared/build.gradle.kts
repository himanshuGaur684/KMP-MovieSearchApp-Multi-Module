import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"

            export(project(":core-network"))
            export(project(":features:search:data"))
            export(project(":features:search:domain"))
            export(project(":features:search:ui"))
            export(project(":features:details:data"))
            export(project(":features:details:domain"))
            export(project(":features:details:ui"))

            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {

        }
        commonMain.dependencies {

            api(projects.coreNetwork)

            api(projects.features.search.data)
            api(projects.features.search.domain)
            api(projects.features.search.ui)

            api(projects.features.details.ui)
            api(projects.features.details.domain)
            api(projects.features.details.data)


            implementation(libs.koin.core)

            // put your Multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        iosMain.dependencies {

        }
    }
}

android {
    namespace = "dev.himanshu.moviesearchapp.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
