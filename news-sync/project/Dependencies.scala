import sbt.*

object Dependencies {
  private object versions {
    val zioTemporal = "0.2.0-M4"
    val zio         = "2.0.12"
    val zioLogging  = "2.1.12"
    val zioJson     = "0.5.0"
    val enumeratum  = "1.7.2"
    val sttp        = "3.8.15"
    val telegramium = "7.67.0"
    val circe       = "0.14.5"
  }

  val zioTemporal = Seq(
    "dev.vhonta" %% "zio-temporal-core"     % versions.zioTemporal,
    "dev.vhonta" %% "zio-temporal-protobuf" % versions.zioTemporal,
    "dev.vhonta" %% "zio-temporal-protobuf" % versions.zioTemporal % "protobuf",
    "dev.vhonta" %% "zio-temporal-testkit"  % versions.zioTemporal % Test,

    // protobuf libs
    "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion,
    "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf"
  )

  val circe = Seq(
    "io.circe" %% "circe-core"           % versions.circe,
    "io.circe" %% "circe-parser"         % versions.circe,
    "io.circe" %% "circe-generic"        % versions.circe,
    "io.circe" %% "circe-generic-extras" % "0.14.3"
  )

  val sttp = Seq(
    "com.softwaremill.sttp.client3" %% "core"  % versions.sttp,
    "com.softwaremill.sttp.client3" %% "circe" % versions.sttp,
    "com.softwaremill.sttp.client3" %% "zio"   % versions.sttp
  )

  val zioBase = Seq(
    "dev.zio" %% "zio"               % versions.zio,
    "dev.zio" %% "zio-logging"       % versions.zioLogging,
    "dev.zio" %% "zio-logging-slf4j" % versions.zioLogging
  )

  val telegramium = Seq(
    "io.github.apimorphism" %% "telegramium-core" % versions.telegramium,
    "io.github.apimorphism" %% "telegramium-high" % versions.telegramium,
    "dev.zio"               %% "zio-interop-cats" % "23.0.0.4"
  )

  val enumeratum = Seq(
    "com.beachape" %% "enumeratum" % versions.enumeratum
  )

  val logback = Seq(
    "ch.qos.logback" % "logback-classic" % "1.2.11"
  )
}
