# 使用 Dotty 编译的 SBT 示例项目

[![Build Status](https://travis-ci.org/Dotty-China/dotty-example-project.svg?branch=master)](https://travis-ci.org/Dotty-China/dotty-example-project)

## 用法

这是一个普通的 sbt 项目, 你可以使用 `sbt compile` 编译它, 使用 `sbt run` 来运行它, 
而 `sbt console` 会启动一个 Dotty REPL.

如果这个项目编译失败了, 那可能是你有一个不适用于 Dotty 的全局 sbt 插件, 
请尝试禁用 `~/.sbt/1.0/plugins` 和 `~/.sbt/1.0` 下的所有插件.

### IDE 支持

Dotty 内置了 IDE 支持, 可以试试看 http://www.dotty-china.org/docs/usage/ide-support.html

## 创建一个新的 Dotty 项目
最快创建 Dotty 项目的方法是使用下面的模板之一: 
* [简单 Dotty 项目](https://github.com/lampepfl/dotty.g8)
* [与 Scala 2 交叉编译的 Dotty 项目](https://github.com/lampepfl/dotty-cross.g8)

## 在已有项目中使用 Dotty

你需要对你的构建配置进行以下调整

### project/plugins.sbt
```scala
addSbtPlugin("ch.epfl.lamp" % "sbt-dotty" % "0.2.2")
```

### project/build.properties
```scala
sbt.version=1.1.6
```

Older versions of sbt are not supported.


### build.sbt
任何以 `0.` 开头的版本号都会被 `sbt-dotty` 插件识别为 Dotty 版本, 你不需要设置任何东西: 

```scala
scalaVersion := "0.8.0-RC1"
```

#### 每日构建
如果最新版本的 Dotty 缺少你需要的错误修复或者功能, 您可能会希望使用每日构建. 
查看 https://repo1.maven.org/maven2/ch/epfl/lamp/dotty_0.8/ 最底部
可以得到版本号最新的每日构建版本. 另外, 你可以设置 `scalaVersion :=
dottyLatestNightlyBuild.get` 来保证总是使用最新的 Dotty 每日构建版本.

## 让你的项目使用 Dotty 进行编译

在移植现有项目时, 开始时通过添加这些到你的 `build.sbt` 中以使用 Scala 2 兼容模式
是一个不错的选择 (请注意, 此模式会影响类型检查, 因而可能不能编译一些有效的 Dotty 代码):

```scala
scalacOptions ++= { if (isDotty.value) Seq("-language:Scala2") else Nil }
```

使用 `isDotty` 设置选项可以确保此编译时选项只用于编译 Dotty.

将代码从 Scala 2.x 移植到 Dotty 的工具目前正在开发中: 
https://github.com/scalacenter/scalafix

如果您的构建配置包含仅针对 Scala 2.x 发布的依赖项， 您可以通过这样替换让他们在Dotty上工作: 

```scala
    libraryDependencies += "a" %% "b" % "c"
```

by:

```scala
    libraryDependencies += ("a" %% "b" % "c").withDottyCompat(scalaVersion.value)
```

这在编译 Scala 2.x 的时候不会产生影响, 但是在编译 Dotty 的时候
它会选择 Scala 2.x 版本的依赖项. 之所以它可以工作, 是因为目前 
Dotty 与 Scala 2.x 兼容

**注意**: Dotty 对 Scala 2.x 的向后兼容性将在 Dotty 正式发布前被删除, 
您不应该依赖它.

## 讨论

随时欢迎您与我们在 [Dotty gitter](http://gitter.im/lampepfl/dotty) 上进行讨论!
