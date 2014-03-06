import org.apache.http.client.methods.HttpGet
import sbt._
import org.apache.http.impl.client.HttpClients
import scala.io.Source

object build extends Build {
  val project = Project(
    "postgression-test",
    file("."),
    settings = Defaults.defaultSettings ++ Seq(

      // Connect to http://www.postgression.com/ to get database config.
      // The intention is to use this database to bootstrap integration tests.
      TaskKey[String]("postgression","Get Postgression server") := {

        // Just for demonstration: this class is from httpclient version 4.1.3
        println(build.getClass.getClassLoader.getResource("org/apache/http/impl/client/DefaultHttpClient.class"))
        // Just for demonstration: this one is from httpclient version 4.3.1
        println(build.getClass.getClassLoader.getResource("org/apache/http/impl/client/HttpClients.class"))

        // This line fails - it loads the wrong HttpClient
        val client = HttpClients.createDefault
        val response = client.execute(new HttpGet("http://api.postgression.com"))
        val db = Source.fromInputStream(response.getEntity.getContent).mkString("")
        println("Postgression connection: "+db)
        db
      }
    )
  )
}
