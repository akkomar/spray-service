package pl.akomar

import java.util.concurrent.Executors

import akka.actor.{ActorRefFactory, Actor}
import grizzled.slf4j.Logger
import spray.routing.HttpService

import scala.concurrent._

class WebApiServiceActor extends Actor with WebApiService {
  def actorRefFactory: ActorRefFactory = context

  def receive: Receive = runRoute(route)

  val operation = LongRunningOperation()
}

trait WebApiService extends HttpService {
  val log = Logger[this.type]

  implicit val ec = ExecutionContext.fromExecutor(Executors.newCachedThreadPool())

  val operation: Operation

  val route =
    pathPrefix("run" / Segment / Segment / Segment) { (pathPart1, pathPart2, pathPart3) =>
      pathEnd {
        get {
          parameterMap { params: Map[String, String] =>
            complete {
              future {
                operation.execute(pathPart1, pathPart2, pathPart3, params)
              }
            }
          }
        }
      }
    }
}