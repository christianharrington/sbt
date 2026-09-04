lazy val checkVirtualized = taskKey[Unit]("asserts -semanticdb-target names a ${ROOT} id")

ThisBuild / scalaVersion := "3.8.4"
ThisBuild / semanticdbEnabled := true

lazy val root = project.in(file("."))

checkVirtualized := Def.uncached {
  val opts = (Compile / compile / scalacOptions).value
  val i = opts.indexOf("-semanticdb-target")
  assert(i >= 0, s"no -semanticdb-target in $opts")
  val target = opts(i + 1)
  assert(target.startsWith("${OUT}/"), s"-semanticdb-target is not virtualized: $target")
}
