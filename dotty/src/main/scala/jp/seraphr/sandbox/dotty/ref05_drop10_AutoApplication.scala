package jp.seraphr.sandbox.dotty

object AutoApplication {
  def method() = ()
  // val t = method // コンパイルエラー

  // Java で定義されたメソッドは AutoApplicationが残る
  val t = 1.toString

  // scala 2で定義されたメソッドも同様
  // Numeric#Ops の toIntは、何故か`()`が付いている
  def toInt[N](n: N)(implicit ev: Numeric[N]): Int = {
    import ev._
    n.toInt
  }
}