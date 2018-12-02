package jp.seraphr.sandbox.dotty

object MatchTypes {
  type Elem[X] = X match {
    case String => Char
    case Array[t] => t
    case Iterable[t] => t
  }

  //  Elem[String]       =:=  Char
  //  Elem[Array[Int]]   =:=  Int
  //  Elem[List[Float]]  =:=  Float
  //  Elem[Nil]          =:=  Nothing
}

object RecursiveMatchTypes {
  // Tupleの結合
  type Concat[+Xs <: Tuple, +Ys <: Tuple] <: Tuple = Xs match {
    case Unit => Ys
    case x *: xs => x *: Concat[xs, Ys]
  }

  val t1: Concat[(String, Int), Unit] = ("a", 1)
  val t2: Concat[(String, Int), (String, Int)] = ("a", 1, "b", 2)

  // 独自Tupleでもやってみる
  sealed trait MyTuple
  class **:[A, B <: MyTuple] extends MyTuple
  object MyNil extends MyTuple

  // Tupleの一番右の型を取り出す
  type Last[X <: MyTuple] = X match {
    case h **: MyNil.type => h
    case h **: t => Last[t]
  }

  val double: Last[Int **: String **: Double **: MyNil.type] = 1.0
}