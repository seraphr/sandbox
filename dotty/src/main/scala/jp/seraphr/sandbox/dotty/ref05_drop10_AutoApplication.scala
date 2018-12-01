package jp.seraphr.sandbox.dotty

object AutoApplication {
  val m1: Unit = ???
  def m2: Unit = ???
  def m3(): Unit = ???

  // => Unit
  // () => Unit
  // (Unit) => Unit

  def method() = ()
  // val t = method // コンパイルエラー

  // Java で定義されたメソッドは AutoApplicationが残る
  val t = 1.toString
}