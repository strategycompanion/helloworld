package com.strategycompanion

import akka.http.scaladsl.server.HttpApp

object HelloHttpServer extends HttpApp{

  def route =
    pathPrefix("v1") {
      path("id" / Segment) { id =>
        get {
          println("server get " + id)
          complete(s"Hello World from akka v1/id="+id)
        } ~
          post {
            entity(as[String]) { entity =>
              println("server get " + entity)
              complete(s"got post request")
            }
          }
      }
    }
}

object HelloHttpServerApplication extends App {
  var port:Int = 8080
  try{
    port = System.getenv("PORT").toInt
  }catch{
    case e: Exception => println("no port assigned, use port 8080.");
  }

    println("start server at port "+port)
    HelloHttpServer.startServer("0.0.0.0", port)
}

//localhost/v1/id/me