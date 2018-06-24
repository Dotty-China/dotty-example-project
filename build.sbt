lazy val root = project
  .in(file("."))
  .settings(
    name := "dotty-example-project",
    description := "使用 Dotty 编译的 SBT 示例项目",
    version := "0.1",

    scalaVersion := "0.8.0"
  )
