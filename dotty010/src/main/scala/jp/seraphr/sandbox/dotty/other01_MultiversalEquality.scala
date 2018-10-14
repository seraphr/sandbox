package jp.seraphr.sandbox.dotty

object MultiversalEquality {
  // import scala.language.strictEquality
  // ドキュメントによると↑をimportしない限り eqAnyが有効らしいんだけど、
  // 関係なく無効になってるっぽい
  implicit def eqAny[A, B]: Eq[A, B] = scala.Eq.eqAny[A, B]
  val t1 = "1" == 1 // Values of types String and Int cannot be compared with == or !=
  val t2 = 1 == 1
}