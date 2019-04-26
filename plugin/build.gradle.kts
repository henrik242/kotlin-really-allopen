plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlin.kapt")
    `maven-publish`
    `java-gradle-plugin`
}

group = "no.synth.kotlin.plugins"
version = "0.1-SNAPSHOT"

val artifactName = "kotlin-really-allopen"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.31")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin-model:1.3.31")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin-api:1.3.31")
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:1.3.31")

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

kapt {
    includeCompileClasspath = false
}