organization  := "pl.akomar"

name := "spray-service"

version := "1.0"

scalaVersion  := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.1.4"
  val sprayV = "1.1.1"
  Seq(
    "io.spray"            %   "spray-can"     % sprayV,
    "io.spray"            %   "spray-routing" % sprayV,
    "io.spray"            %   "spray-testkit" % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-slf4j"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.specs2"          %%  "specs2"        % "2.2.3" % "test",
    "ch.qos.logback"      %   "logback-classic" % "1.0.12",
    "org.clapper" %% "grizzled-slf4j" % "1.0.1",
    "org.specs2"          %%  "specs2"        % "2.2.3" % "test",
    "org.mockito" % "mockito-core" % "1.9.5"
  )
}

Revolver.settings