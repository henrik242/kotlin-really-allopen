kotlin-really-allopen
=====================

Compiler plugin for Gradle that removes the `final` restriction for all classes.

This is different from [kotlin-allopen](https://kotlinlang.org/docs/reference/compiler-plugins.html#all-open-compiler-plugin)
in that you don't have to specify which classes to open up.


Work in progress
================

This somehow doesn't work yet.  Steps to reproduce:

1) `./gradlew :plugin:build :plugin:publishToMavenLocal`

2) Uncomment either of the `apply` lines in `example/build.gradle.kts`

3) `./gradlew :example:build -s`

4) BAM! Build fails with 
   `org.gradle.api.plugins.UnknownPluginException: Plugin with id 'kotlin-really-allopen' not found.`
    
