package jp.seraphr.sandbox.dotty

object ImplicitConversions {
  // abstract class ImplicitConverter[-T, +U] extends Function1[T, U]
  // が導入され、型の暗黙的変換は、ImplicitConverterが無いとダメになたt

  import scala.language.implicitConversions
  // def ng(implicit ev: Int => String): String = 10
  def ok(implicit ev: ImplicitConverter[Int, String]): String = 10
}