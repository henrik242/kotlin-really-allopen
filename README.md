kotlin-really-allopen
=====================

Compiler plugin for Gradle that removes the `final` restriction for all classes.

This is different from [kotlin-allopen](https://kotlinlang.org/docs/reference/compiler-plugins.html#all-open-compiler-plugin)
in that you don't have to specify which classes to open up.

Building
--------

`./gradlew :plugin:build`

Testing
-------

`./gradlew :plugin:build :plugin:publishToMavenLocal :example:build`

Credits
-------

I have used [kotlin-allopen](https://github.com/JetBrains/kotlin/tree/master/plugins/allopen)
[(2)](https://github.com/JetBrains/kotlin/tree/master/libraries/tools/kotlin-allopen)
and [sample-kotlin-compiler-plugin](https://github.com/Takhion/sample-kotlin-compiler-plugin)
as inspiration for this plugin.
