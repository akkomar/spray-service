package pl.akomar

import akka.actor.{ActorRefFactory, Actor}
import spray.routing.HttpService

class WebApiServiceActor extends Actor with WebApiService {
  def actorRefFactory: ActorRefFactory = context

  def receive: Receive = runRoute(route)
}

trait WebApiService extends HttpService {
  val route =
    path("") {
      get {
        complete {
          <html>
            <body>
              <h1>Hello world!</h1>
            </body>
          </html>

        }
      }
    }
}