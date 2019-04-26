package no.synth.kotlin.plugins.reallyallopen

import spock.lang.Specification

class PluginTest extends Specification {

    def "final class should be opened"() {
      given:
        def mockedClass = Mock(SomeFinalClass)
        mockedClass.someFinalMethod() >> "notcheese"

      expect:
        mockedClass.someFinalMethod() == "cheese"
    }
}
