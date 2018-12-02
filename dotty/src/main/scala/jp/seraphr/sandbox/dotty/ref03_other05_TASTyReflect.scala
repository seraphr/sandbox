package jp.seraphr.sandbox.dotty

object TastyReflect {
  import scala.quoted._
  import scala.tasty._

  inline def natConst(x: Int): Int = ~natConstImpl('(x))

  def natConstImpl(x: Expr[Int])(implicit reflection: Reflection): Expr[Int] = {
    import reflection._
    // ドキュメント上は unseal になっている。 最近のコミット( 1af9030c8a01727d )で unseal になったっぽい
    val xTree: Term = x.reflect
    xTree match {
      case Term.Literal(Constant.Int(n)) =>
        if (n <= 0)
          throw new QuoteError("Parameter must be natural number")
        n.toExpr
      case _ =>
        throw new QuoteError("Parameter must be a known constant")
    }
  }
}

// InspectTastyFile 用 object
// コンパイル単位をわけないと、no class file was found
object Hoge {
  val fuga = 1
}
