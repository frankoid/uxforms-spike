
import scala.concurrent.duration._

val basePackage = "com.example.form" // TODO: Change this to be the package in which your FormDefinitionFactory and Activator classes live

enablePlugins(SbtOsgi, FormDefinitionPlugin, BuildInfoPlugin)

buildInfoPackage := s"$basePackage.build"

buildInfoKeys := Seq[BuildInfoKey](
  name,
  "artifact" -> (artifactPath in(Compile, packageBin)).value,
  themeName,
  retentionPeriod
)

buildInfoObject := "MyFormDefinitionBuildInfo"

organization := "com.example" // TODO: Change this to be your organisation

name := "uxforms-spike-francis" // TODO: Change this to be the path on which your form will be deployed

themeName := "uxforms" // TODO: Change this to the name of the theme to use in your form

retentionPeriod := 30.minutes // TODO: Change this to the length of time form data should be stored. I.e. the duration of the user's session.

formDefinitionClass := s"$basePackage.MyFormDefinitionFactory" // TODO: Change this to be the fully qualified class name of your FormDefinitionFactory

scalaVersion := "2.11.7"

test := ((test in Test) dependsOn OsgiKeys.bundle).value

libraryDependencies ++= {
  Seq(
    "com.uxforms" %% "test" % "15.16.+" % Test,
    "org.scalatest" %% "scalatest" % "2.2.4" % Test
  )
}

// We must export our form definition factory and activator's package to the OSGi context
// so that UX Forms can load and execute this form definition.
OsgiKeys.exportPackage := Seq(s"$basePackage.*")

OsgiKeys.bundleActivator := Some(s"$basePackage.Activator")

// Force the build to use Java 8
javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

scalacOptions := Seq("-target:jvm-1.8")

OsgiKeys.requireCapability := "osgi.ee;filter:=\"(&(osgi.ee=JavaSE)(version=1.8))\""
