package jp.seraphr.sandbox.dotty

object EtaExpansion {
  // _ が要らなくなった。　将来的にdeprecatedになる

  def m(x: Boolean, y: String)(z: Int): List[Int] = List()
  val f1 = m                // (Boolean, String) => Int => List[Int]
  val f2 = m(true, "abc")   // Int => List[Int]

  // 空の引数リストのメソッドは、自動的には展開されない
  // Auto-Applicationと競合するため
  def m2(): Int = 10
  // val f3 = m2
}