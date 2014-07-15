package pl.akomar

import akka.actor.ActorRefFactory
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes._

class WebApiServiceSpec extends Specification with Specs2RouteTest with WebApiService with Mockito {
  def actorRefFactory: ActorRefFactory = system

  val operation = mock[Operation]
  operation.execute(anyString, anyString, anyString, any[Map[String, String]]) returns "test"

  "WebApiService" should {
    "return path segments" in {
      Get("/run/a/b/c") ~> route ~> check {
        responseAs[String] must contain("test")
      }
    }
  }
}
