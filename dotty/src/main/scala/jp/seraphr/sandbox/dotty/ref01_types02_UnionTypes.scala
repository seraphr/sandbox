package jp.seraphr.sandbox.dotty

object UnionTypes {
  def method(a: Int | String): Unit = a match {
    case i: Int => println("int!")
    case s: String => println("string!")
  }

  // literal type + union type は現状エラーとなる。 
  // Singleton type Int(2) is not allowed in a union type
  // https://github.com/lampepfl/dotty/issues/1551
  // def method2(a: 1 | 2): Unit = ()
}