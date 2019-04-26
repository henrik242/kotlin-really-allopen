plugins {
    kotlin("jvm") version "1.3.31"
    id("org.jetbrains.kotlin.kapt") version "1.3.31"
    `maven-publish`
}

group = "no.synth.kotlin.plugins"
version = "0.1-SNAPSHOT"

val artifactName = "kotlin-really-allopen"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.31")
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:1.3.31")

    implementation("com.google.auto.service:auto-service:1.0-rc5")
    kapt("com.google.auto.service:auto-service:1.0-rc5")
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Implementation-Title"] = artifactName
        attributes["Implementation-Version"] = project.version
    }
    exclude("META-INF/*kotlin_module")

    baseName = artifactName
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

kapt {
    includeCompileClasspath = false
}