package jp.seraphr.sandbox.dotty

// 何故かドキュメントの目次からリンクが消えていた・・・
// http://dotty.epfl.ch/docs/reference/singleton-types.html
object LiteralSingletonTypes {
  val t: 42 = 42
  var x: "Jedi" = "Jedi"

  trait Mat[N <: Singleton, M <: Singleton] {
    def add(aThat: Mat[N, M]): Mat[N, M]
  }

  val m1: Mat[2, 3] = ???
  val m2: Mat[2, 3] = ???
  val m3: Mat[4, 3] = ???

  m1.add(m2)
  // m1.add(m3) // コンパイルエラー
}