name := """mutants"""
organization := "com.mercadolibre"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies += guice

scalaVersion := "2.12.4"

libraryDependencies += guice
