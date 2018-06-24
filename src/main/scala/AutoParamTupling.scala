
/**
  * 函数参数自动元组化: http://www.dotty-china.org/docs/reference/auto-parameter-tupling.html
  */
object AutoParamTupling {

  def test: Unit = {

    /**
      * 为了保证线程安全, 你需要将 @volatile 放在惰性值之前.
      * http://www.dotty-china.org/docs/reference/changed/lazy-vals.html
      */
    @volatile lazy val xs: List[String] = List("d", "o", "t", "t", "y")

    /**
      * 当前在 Scala 2.12.2 中的行为:
      * error: missing parameter type
      * Note: The expected type requires a one-argument function accepting a 2-Tuple.
      * Consider a pattern matching anonymous function, `{ case (s, i) =>  ... }`
      */
    xs.zipWithIndex.map((s, i) => println(s"$i: $s"))

  }
}