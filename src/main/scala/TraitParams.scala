/**
  * 特质参数: http://www.dotty-china.org/docs/reference/trait-parameters.html
  */
object TraitParams {

  trait Base(val msg: String)
  class A extends Base("Hello")
  class B extends Base("Dotty!")

  // 并集类型只在 Dotty 中存在, 所以不会在 Scala 2 中被编译
  private def printMessages(msgs: (A | B)*) = println(msgs.map(_.msg).mkString(" "))

  def test: Unit = {

    printMessages(new A, new B)

    // 合理的检查类路径: 如果 dotty jar 不存在, 这将不会被运行
    val x: Int => Int = z => z
    x(1)
  }
}
