package pl.akomar

import java.util.concurrent.Executors

import akka.actor.{ActorRefFactory, Actor}
import grizzled.slf4j.Logger
import spray.routing.HttpService

import scala.concurrent._

class WebApiServiceActor extends Actor with WebApiService {
  def actorRefFactory: ActorRefFactory = context

  def receive: Receive = runRoute(route)
}

trait WebApiService extends HttpService {
  val log = Logger[this.type]

  implicit val ec = ExecutionContext.fromExecutor(Executors.newCachedThreadPool())

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

  def longRunningOp(a: String, b: String, c: String): Future[String] = future {
    log.info("Starting long running operation...")
    Thread.sleep(1000 * 30);
    log.info("Long running operation finished, returning result.")
    s"pathPart1=$a" + "\n" +
      s"pathPart2=$b" + "\n" +
      s"pathPart3=$c"
  }
}