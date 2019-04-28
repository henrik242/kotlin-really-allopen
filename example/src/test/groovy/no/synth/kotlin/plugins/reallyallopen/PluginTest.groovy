package no.synth.kotlin.plugins.reallyallopen

import org.spockframework.mock.CannotCreateMockException
import spock.lang.Specification

class PluginTest extends Specification {

    def "final class should be open"() {
      when:
        Mock(SomeFinalClass)

      then:
        notThrown(CannotCreateMockException)
    }

    def "final function should be open"() {
      when:
        def mockedClass = Mock(SomeFinalClass)
        1 * mockedClass.someFinalMethod() >> "mock cheese"

      then:
        mockedClass.someFinalMethod() == "mock cheese"
    }
}
