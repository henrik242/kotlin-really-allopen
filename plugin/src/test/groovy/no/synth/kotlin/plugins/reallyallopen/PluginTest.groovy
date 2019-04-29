package no.synth.kotlin.plugins.reallyallopen

import spock.lang.Specification

class PluginTest extends Specification {

    // Doesn't work from IntelliJ (yet)
    def "parses version"() {
      expect:
        ReallyAllOpenPluginKt.groupId != null
        ReallyAllOpenPluginKt.artifactId != null
        ReallyAllOpenPluginKt.version != null
        ReallyAllOpenPluginKt.version.size() >= 3
        ReallyAllOpenPluginKt.version.contains(".")
    }
}
