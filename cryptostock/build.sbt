ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.2.2"

lazy val root = (project in file("."))
  .settings(
    name := "func-scala-2022-zio-temporal"
  )
  .aggregate(cryptostock)
  .disablePlugins(ProtocPlugin)

lazy val cryptostock = project
  .in(file("cryptostock"))
  .settings(
    name := "cryptostock",
    libraryDependencies ++= {
      val zioTemporalVersion = "0.2.0-RC1"
      val zioVersion         = "2.0.12"
      val zioLoggingVersion  = "2.1.12"

      val zioTemporal = Seq(
        "dev.vhonta" %% "zio-temporal-core"     % zioTemporalVersion,
        "dev.vhonta" %% "zio-temporal-protobuf" % zioTemporalVersion,
        "dev.vhonta" %% "zio-temporal-protobuf" % zioTemporalVersion % "protobuf",
        "dev.vhonta" %% "zio-temporal-testkit"  % zioTemporalVersion % Test,

        // protobuf libs
        "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion,
        "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf"
      )

      val zioDeps = Seq(
        "dev.zio" %% "zio"               % zioVersion,
        "dev.zio" %% "zio-logging"       % zioLoggingVersion,
        "dev.zio" %% "zio-logging-slf4j" % zioLoggingVersion
      )

      val miscDeps = Seq(
        "ch.qos.logback" % "logback-classic" % "1.2.11"
      )

      zioDeps ++ zioTemporal ++ miscDeps
    },
    Compile / PB.targets := Seq(
      scalapb.gen(
        flatPackage = true,
        grpc = false
      ) -> (Compile / sourceManaged).value / "scalapb"
    ),

    // mac m1 workaround
    PB.protocDependency := {
      System.getProperty("os.arch") match {
        case "aarch64" =>
          // mac m1 workaround
          ("com.google.protobuf" % "protoc" % PB.protocVersion.value).artifacts(
            Artifact(
              name = "protoc",
              `type` = PB.ProtocBinary,
              extension = "exe",
              classifier = "osx-x86_64"
            )
          )

        case _ => PB.protocDependency.value
      }
    }
  )
