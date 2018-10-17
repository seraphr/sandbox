package jp.seraphr.sandbox.dotty

object VolatileLazyVals {
  // lazy val がスレッドセーフじゃなくなりました
  lazy val hoge = 10
  
  // thread safeにしたいときは volatileつけましょう
  @volatile
  lazy val fuga = 10
}