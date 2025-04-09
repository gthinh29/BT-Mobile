// Sử dụng Kotlin DSL syntax
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Dùng dấu ngoặc đơn và ký hiệu Kotlin
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.android.tools.build:gradle:7.3.1")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

// Cấu hình thư mục build (Kotlin DSL)
val newBuildDir: Directory = rootProject.layout.buildDirectory.dir("../../build").get()
rootProject.layout.buildDirectory.set(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.set(newSubprojectBuildDir)
    project.evaluationDependsOn(":app")
}

// Task clean
tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}