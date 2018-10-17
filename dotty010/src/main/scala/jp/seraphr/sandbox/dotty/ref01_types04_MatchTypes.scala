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