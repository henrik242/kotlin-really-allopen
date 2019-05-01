import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.31"
    kotlin("kapt") version "1.3.31"
    id("com.gradle.plugin-publish") version "0.10.1"
    `maven-publish`
    `java-gradle-plugin`
}

group = "no.synth"
version = "0.4-SNAPSHOT" // Remember to set version in ReallyAllOpenPlugin.kt as well

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin-model")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin-api")
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable")

    implementation("com.google.auto.service:auto-service:1.0-rc5")
    kapt("com.google.auto.service:auto-service:1.0-rc5")
}

gradlePlugin {
    plugins {
        create(project.name) {
            displayName = "Kotlin Really All Open compiler plugin"
            description = "Removes final restriction from all Kotlin classes"
            id = "${project.group}.${project.name}"
            implementationClass = "no.synth.reallyallopen.ReallyAllOpenGradlePlugin"
        }
    }
}

pluginBundle {
    website = "https://github.com/henrik242/kotlin-really-allopen"
    vcsUrl = "https://github.com/henrik242/kotlin-really-allopen.git"
    tags = listOf("kotlin")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

kapt {
    includeCompileClasspath = false
}