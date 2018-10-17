
object OptionLessPatternMatching {
  // もっと変わるかも？

  // boolean パターン
  // unapplyでBooleanを返す
  object Even {
    def unapply(s: String): Boolean = s.size % 2 == 0
  }


  // Product Pattern
  // unapplyでProductを継承して def / valで_Nの名前を持つオブジェクトを返す
  class FirstChars(s: String) extends Product {
    def _1 = s.charAt(0)
    def _2 = s.charAt(1)

  // Not used by pattern matching: Product is only used as a marker trait.
    def canEqual(that: Any): Boolean = ???
    def productArity: Int = ???
    def productElement(n: Int): Any = ???
  }
  object FirstChars {
    def unapply(s: String): FirstChars = new FirstChars(s)
  }


  // Name-based Seq Pattern
  // unapplySeqで以下のXを取得できるオブジェクトを返す
  type T1
  type T2 <: T1
  type T3 <: T1
  type X = {
    def lengthCompare(len: Int): Int // or, `def length: Int`
    def apply(i: Int): T1
    def drop(n: Int): scala.Seq[T2]
    def toSeq: scala.Seq[T3]
  }
  object CharList {
    def unapplySeq(s: String): Option[Seq[Char]] = Some(s.toList)
  }

  // Name-based Pattern
  // unapplyで isEmpty と get を持っているオブジェクトを返す
  // もしくは isEmptyと _N を持っているオブジェクトを返す
  class Nat(val x: Int) {
    def get: Int = x
    def isEmpty = x < 0
  }

  object Nat {
    def unapply(x: Int): Nat = new Nat(x)
  }
}