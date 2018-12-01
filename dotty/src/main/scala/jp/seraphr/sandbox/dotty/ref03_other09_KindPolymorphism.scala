package jp.seraphr.sandbox.dotty

// "-Ykind-polymorphism" が必要
object KindPolymorphism {
  trait Foo[+A <: AnyKind] {
    def rank: Int
  }

  case class FooImpl(rank: Int) extends Foo[Nothing]
  case class Triple[A, B, C]()

  object Foo {
    implicit def foo1: Foo[List] = FooImpl(1)
    implicit def foo2: Foo[Map] = FooImpl(2)
    implicit def foo3: Foo[Triple] = FooImpl(3)
  }

  def foo[A <: AnyKind](implicit ev: Foo[A]): Int = ev.rank

  def main(args: Array[String]): Unit = {
    println(foo[List])    // => 1
    println(foo[Map])     // => 2
    println(foo[Triple])  // => 3
  }
}