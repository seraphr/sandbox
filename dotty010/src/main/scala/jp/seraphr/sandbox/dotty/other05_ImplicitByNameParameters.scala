package jp.seraphr.sandbox.dotty

// scala 2.13にも入る
object ImplicitByNameParameters {
  trait Foo {
    def next: Foo
  }

  // rec を by-nameにしないとコンパイルエラー
  // in dotty 0.10
  // object Foo produces a diverging implicit search

  // in scala 2.12.6
  //  diverging implicit expansion for type ImplicitByNameParameters.Foo
  object Foo {
    implicit def foo(implicit rec: =>Foo): Foo =
      new Foo { def next = rec }
  }

  val foo = implicitly[Foo]
  def main(args: Array[String]): Unit = {
    println(foo == foo.next) // false
  }
}