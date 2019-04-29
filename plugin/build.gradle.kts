import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.31"
    kotlin("kapt") version "1.3.31"
    `maven-publish`
    `java-gradle-plugin`
}

group = "no.synth.kotlin.plugins"
version = "0.1-SNAPSHOT"

val artifactName = "kotlin-really-allopen"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin-model")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin-api")
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable")

    implementation("com.google.auto.service:auto-service:1.0-rc5")
    kapt("com.google.auto.service:auto-service:1.0-rc5")
}

val jar by tasks.getting(Jar::class) {
    baseName = artifactName
}

gradlePlugin {
    plugins {
        create(artifactName) {
            id = artifactName
            implementationClass = "no.synth.kotlin.plugins.reallyallopen.ReallyAllOpenGradlePlugin"
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = artifactName

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