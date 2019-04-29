plugins {
    groovy
}

dependencies {
    implementation(project(":kotlin-really-allopen"))

    testImplementation(gradleTestKit())
    testImplementation("net.bytebuddy:byte-buddy:1.9.12")
    testImplementation("org.spockframework:spock-core:1.3-groovy-2.5")
}
