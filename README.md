# sbt problem - sample project

This project is a simplified example of an sbt project with a version conflict
between "org.apache.httpcomponents" % "httpclient" version 4.1.3 (comes with sbt?)
and version 4.3.1.

The reason why I can't just use version 4.1.3 is that the project also uses
a plugin that requires version 4.3.1.

To reproduce the error, run `sbt postgression`.