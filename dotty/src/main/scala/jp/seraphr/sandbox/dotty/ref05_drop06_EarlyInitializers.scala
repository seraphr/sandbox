package jp.seraphr.sandbox.dotty

object EarlyInitializers {
  // 初期化順序をいじる構文。
  // trait parameters 使えば良い


  trait A {
    val a: String
    val b = a // 普通に継承すると、bがnullになる！
  }
  // class C extends { val a = "hoge"} with A
}