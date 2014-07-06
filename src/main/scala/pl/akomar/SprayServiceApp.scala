package pl.akomar

import akka.actor.ActorSystem

object SprayServiceApp extends App {
  implicit val system = ActorSystem("spray-service-app")
}
