repositories {
    mavenLocal()
}
gradle.settingsEvaluated {
     settings.pluginManagement {
         repositories {
             mavenLocal()
             gradlePluginPortal()
             jcenter()
         }
     }
 }
plugins {
    kotlin("jvm")
    groovy
    id("kotlin-really-allopen") version "0.1-SNAPSHOT"
}

dependencies {
    implementation(project(":plugin"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.31")

    testImplementation("net.bytebuddy:byte-buddy:1.9.12")
    testImplementation("org.spockframework:spock-core:1.3-groovy-2.5")
}
