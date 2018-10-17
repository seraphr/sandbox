package jp.seraphr.sandbox.dotty

object AutoApplication {
  def method() = ()
  // val t = method // コンパイルエラー

  // Java で定義されたメソッドは AutoApplicationが残る
  val t = 1.toString
}