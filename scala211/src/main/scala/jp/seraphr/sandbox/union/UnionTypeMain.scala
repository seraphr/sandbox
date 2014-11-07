package jp.seraphr.sandbox.union

/**
 */
//object UnionTypeMain extends App {
//  import UnionType.CT
//  println(acceptStringOrInt("hoge")(UnionType.Instance1[CT[String]#T, CT[Int]#T, String]))
//  //  println(acceptStringOrInt(42))
//  //  println(acceptStringOrInt(42.0))
//  println(acceptDouble(42.0))
//
//  //  println(accept345(Type4(1)))
//
//  def acceptDouble[T: UnionType.U1[Double]#U](a: T) = a
//
//  def acceptStringOrInt[T: UnionType.U2[String, Int]#U](a: T) = a match {
//    case _: String => s"a is String"
//    case _: Int    => s"a is Int"
//  }
//
//  case class Type1(a: Int)
//  case class Type2(a: Int)
//  case class Type3(a: Int)
//  case class Type4(a: Int)
//  case class Type5(a: Int)
//
//  type UU = UnionType.U3[Type2, Type3, Type4]
//  def accept345[T: UU#U](a: T) = a match {
//    case _: Type2 => s"$a is Type2"
//    case _: Type3 => s"$a is Type3"
//    case _: Type4 => s"$a is Type4"
//  }
//}
