buildscript {
    repositories {
        mavenLocal()
    }

    dependencies {
        classpath("no.synth.kotlin.plugins:kotlin-really-allopen:0.1-SNAPSHOT")
    }
}

plugins {
    kotlin("jvm")
    groovy
}
// AAAARGH this fails
//apply(plugin = "kotlin-really-allopen")

// AAAARGH this fails too
//apply(plugin = "no.synth.kotlin.plugins.kotlin-really-allopen")

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.31")
    testImplementation("org.spockframework:spock-core:1.3-groovy-2.5")
}
