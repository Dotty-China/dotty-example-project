/**
  * 并集类型: http://www.dotty-china.org/docs/reference/union-types.html
  */
object UnionTypes {

  sealed trait Division
  final case class DivisionByZero(msg: String) extends Division
  final case class Success(double: Double) extends Division

  // 你可以为并集类型创建类型别名 (sum types).
  type DivisionResult = DivisionByZero | Success

  sealed trait List[+A]
  final case class Empty() extends List[Nothing]
  final case class Cons[+A](h: A, t: List[A]) extends List[A]

  private def safeDivide(a: Double, b: Double): DivisionResult = {
    if (b == 0) DivisionByZero("DivisionByZeroException") else Success(a / b)
  }

  private def either(division: Division) = division match {
    case DivisionByZero(m) => Left(m)
    case Success(d) => Right(d)
  }

  def test: Unit = {

    val divisionResultSuccess: DivisionResult = safeDivide(4, 2)

    // 可交换
    val divisionResultFailure: Success | DivisionByZero = safeDivide(4, 0)

    // 用并集类型的对象调用 `either` 方法.
    println(either(divisionResultSuccess))

    // 用并集类型的对象调用 `either` 方法.
    println(either(divisionResultFailure))

    val list: Cons[Int] | Empty = Cons(1, Cons(2, Cons(3, Empty())))
    val emptyList: Empty | Cons[Any] = Empty()
    println(list)
    println(emptyList)

  }
}
