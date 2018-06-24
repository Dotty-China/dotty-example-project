import scala.language.strictEquality

/**
  * 多元相等: http://www.dotty-china.org/docs/reference/multiversal-equality.html
  * scala.Eq 定义在: https://github.com/lampepfl/dotty/blob/master/library/src/scala/Eq.scala
  */
object MultiversalEquality {

  def test: Unit = {

    // Int 与 String 类型的值不能够用 == 或 != 进行比较,
    // 除非我们提供一个这样的隐式参数:
    implicit def eqIntString: Eq[Int, String] = Eq
    println(3 == "3")

    //默认情况下, 所有数值都能够互相比较, 因为我们有: 
    // implicit def eqNumber : Eq[Number, Number] = Eq
    println(3 == 5.1)

    // 默认情况下所有序列都能够互相比较, 因为我们有:
    // implicit def eqSeq[T, U](implicit eq: Eq[T, U]): Eq[Seq[T], Seq[U]] = Eq
    println(List(1, 2) == Vector(1, 2))

    class A(a: Int)
    class B(b: Int)

    val a = new A(4)
    val b = new B(4)

    // scala.language.strictEquality 被启用, 
    // 此时我们需要提供这样两个隐式参数才能将 a 与 b 进行比较:
    implicit def eqAB: Eq[A, B] = Eq
    implicit def eqBA: Eq[B, A] = Eq

    println(a != b)
    println(b == a)
  }
}
