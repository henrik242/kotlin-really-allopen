[![Build Status](https://travis-ci.org/henrik242/kotlin-really-allopen.svg?branch=master)](https://travis-ci.org/henrik242/kotlin-really-allopen)

kotlin-really-allopen
=====================

Compiler plugin for Gradle that removes the `final` restriction for all classes and functions.

This is different from [kotlin-allopen](https://kotlinlang.org/docs/reference/compiler-plugins.html#all-open-compiler-plugin)
 in that you don't have to specify which classes to open up.

Usage
-----

Add [the usual plugin definition](https://plugins.gradle.org/plugin/no.synth.kotlin-really-allopen) to your
 `build.gradle`:
````
plugins {
  id "no.synth.kotlin-really-allopen" version "0.3"
}
````
For [reasons still unknown](https://github.com/henrik242/kotlin-really-allopen/issues/16) you need to add 
 `gradlePluginPortal()` to the root-level `repositories` definition as well:
```
repositories {
   gradlePluginPortal()
}
```

Building & testing
------------------

`./gradlew :kotlin-really-allopen:build :kotlin-really-allopen:publishToMavenLocal :functional-test:build`

Credits
-------

I have used [kotlin-allopen](https://github.com/JetBrains/kotlin/tree/master/plugins/allopen)
 [(2)](https://github.com/JetBrains/kotlin/tree/master/libraries/tools/kotlin-allopen)
 and [sample-kotlin-compiler-plugin](https://github.com/Takhion/sample-kotlin-compiler-plugin)
 as inspiration for this plugin.
