package pl.akomar

import grizzled.slf4j.Logger

trait Operation {
  val log = Logger[this.type]

  def execute(a: String, b: String, c: String, params: Map[String, String]): String
}

class LongRunningOperation extends Operation {
  override def execute(a: String, b: String, c: String, params: Map[String, String]): String = {
    log.info("Starting long running operation...")
    Thread.sleep(/*1000 * */ 30);
    log.info("Long running operation finished, returning result.")
    s"pathPart1=$a" + "\n" +
      s"pathPart2=$b" + "\n" +
      s"pathPart3=$c" + "\n" +
      s"parameters: " + params.mkString(", ")
  }
}

object LongRunningOperation {
  def apply() = new LongRunningOperation()
}
