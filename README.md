kotlin-really-allopen
=====================

Compiler plugin for Gradle that removes the `final` restriction for all classes.

This is different from [kotlin-allopen](https://kotlinlang.org/docs/reference/compiler-plugins.html#all-open-compiler-plugin)
in that you don't have to specify which classes to open up.

NB! Work in progress!
---------------------

1) Comment the classpath and plugin for `really-allopen` in example/build.gradle.kts before building the
first time.

2) Functions are still not opened 
    
Credits
-------

I have used [kotlin-allopen](https://github.com/JetBrains/kotlin/tree/master/plugins/allopen)
and [sample-kotlin-compiler-plugin](https://github.com/Takhion/sample-kotlin-compiler-plugin)
as inspiration for this plugin.
