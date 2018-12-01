package jp.seraphr.sandbox.dotty

object MatchTypes {
  type Elem[X] = X match {
    case String => Char
    case Array[t] => t
    case Iterable[t] => t
  }
  type Elem2[X <: Number] = X match {
    case Integer => Char
  }

  type A = Elem2[Integer]
  val a: A = 'a'

  //  Elem[String]       =:=  Char
  //  Elem[Array[Int]]   =:=  Int
  //  Elem[List[Float]]  =:=  Float
  //  Elem[Nil]          =:=  Nothing
}