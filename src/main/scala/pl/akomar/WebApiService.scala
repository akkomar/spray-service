package pl.akomar

import akka.actor.{ActorRefFactory, Actor}
import spray.routing.HttpService

class WebApiServiceActor extends Actor with WebApiService {
  def actorRefFactory: ActorRefFactory = context

  def receive: Receive = runRoute(route)
}

trait WebApiService extends HttpService {
  val route =
    pathPrefix("run" / Segment / Segment / Segment) { (pathPart1, pathPart2, pathPart3) =>
      pathEnd {
        get {
          complete {
            s"pathPart1=$pathPart1" + "\n" +
              s"pathPart2=$pathPart2" + "\n" +
              s"pathPart3=$pathPart3"
          }
        }
      }
    }
}