package pl.akomar

import akka.actor.{ActorRefFactory, Actor}
import spray.routing.HttpService

import scala.concurrent.Future

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
            longRunningOp(pathPart1, pathPart2, pathPart3)
          }
        }
      }
    }

  def longRunningOp(a: String, b: String, c: String) = {
    Thread.sleep(1000 * 60);

    s"pathPart1=$a" + "\n" +
      s"pathPart2=$b" + "\n" +
      s"pathPart3=$c"
  }
}