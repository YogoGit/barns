name := "Barn Management System"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "com.h2database" % "h2" % "1.4.181",
  "org.springframework" % "spring-jdbc" % "4.1.1.RELEASE",
  "org.springframework" % "spring-context" % "4.1.1.RELEASE",
  "org.springframework" % "spring-orm" % "4.1.1.RELEASE",
  "org.springframework" % "spring-jdbc" % "4.1.1.RELEASE",
  "org.springframework" % "spring-tx" % "4.1.1.RELEASE",
  "org.springframework" % "spring-expression" % "4.1.1.RELEASE",
  "org.springframework" % "spring-aop" % "4.1.1.RELEASE",
  "org.springframework" % "spring-test" % "4.1.1.RELEASE" % "test",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.6.Final",
  "com.novocode" % "junit-interface" % "0.11" % "test->default",
  "junit" % "junit" % "4.7" % "test",
  "mysql" % "mysql-connector-java" % "5.1.35",
  "org.webjars" % "bootstrap" % "2.1.1"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)