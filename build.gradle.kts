import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
plugins {
    kotlin("jvm") version "1.9.10"
}
allprojects {
    buildscript {
        repositories {
            mavenCentral()
        }
    }
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation(kotlin("test"))
    }

    tasks.test {
        useJUnitPlatform()
        testLogging {
            setExceptionFormat("full")
        }
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
}