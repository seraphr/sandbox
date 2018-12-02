package jp.seraphr.sandbox.dotty

object MetaPrograming {
  import scala.quoted._

  def booleanExpr: Expr[Boolean] = '(true)
  inline def trueImpl = ~booleanExpr

  val tBooleanType: Type[Boolean] = '[Boolean]
  inline def trueOrFalse: Boolean = ~trueOrFalseImpl
  def trueOrFalseImpl: Expr[Boolean] = '{
    type Bool = ~tBooleanType
    val tTrue: Bool = true
    tTrue || false
  }
  
  // splice outside quotes or inline method
  // val tBool = ~tBooleanExpr
  // type Bool = ~tBooleanType
}

object MetaListMap {
  import scala.quoted._
  inline def map[A, B](aList: List[A])(f: A => B): List[B] = {
    ~mapImpl('[B], '(aList), '(f))
  }

  private def mapImpl[A: Type, B](
      aTB: Type[B],
      aList: Expr[List[A]], 
      f: Expr[A => B]) = '{

        var tList = ~aList
        val tLen = tList.length
        val tBuffer = new scala.collection.mutable.ListBuffer[~aTB]
        while(tList.nonEmpty) {
          tBuffer.append(~f('(tList.head)))
          tList = tList.tail
        }

        tBuffer.result
      }
}