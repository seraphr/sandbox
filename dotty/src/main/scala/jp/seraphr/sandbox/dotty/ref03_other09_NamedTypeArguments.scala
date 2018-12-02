package jp.seraphr.sandbox.dotty

object NamedTypeArguments {
  def construct[Elem, Coll[_]](xs: Elem*): Coll[Elem] = ???

  val xs2 = construct[Coll = List, Elem = Int](1, 2, 3)

  // 一部だけ指定して残りを推論させられる！！
  val xs3 = construct[Coll = List](1, 2, 3)
}