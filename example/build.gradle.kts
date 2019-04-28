buildscript {
    repositories {
        mavenLocal()
    }

    dependencies {
        classpath("no.synth.kotlin.plugins:kotlin-really-allopen:0.1-SNAPSHOT")
    }
}

repositories {
    mavenLocal()
}

plugins {
    kotlin("jvm")
    groovy
}
apply(plugin = "kotlin-really-allopen")

dependencies {
    implementation(project(":plugin"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.31")

    testImplementation("net.bytebuddy:byte-buddy:1.9.12")
    testImplementation("org.spockframework:spock-core:1.3-groovy-2.5")
}
