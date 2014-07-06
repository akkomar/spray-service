package pl.akomar

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http

object SprayServiceApp extends App {
  implicit val system = ActorSystem("spray-service-app")

  val webApiService = system.actorOf(Props[WebApiServiceActor])

  IO(Http) ! Http.Bind(webApiService, "localhost")
}
