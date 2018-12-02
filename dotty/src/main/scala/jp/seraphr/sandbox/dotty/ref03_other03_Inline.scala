package jp.seraphr.sandbox.dotty

object Inline {
  inline val flag = false
  // right-hand side of inline value readLine must be a constant expression
  // inline val readLine = System.in.read()

  inline def checkFlag[T](ifTrue: =>T, ifFalse: =>T): T = {
    if(flag) ifTrue else ifFalse
  }

  inline def inlineArg(inline v: Int): Int = v + 2
  val v = inlineArg(4)
  // argument to inline parameter must be a constant expression
  // inlineArg("4".toInt)
}

object InlinePow {
  inline def power(x: Double, n: Int): Double =
    if (n == 0) 1.0
    else if (n == 1) x
    else {
      val y = power(x, n / 2)
      if (n % 2 == 0) y * y else y * y * x
    }

  def expr = "10.0".toDouble
  val pow10_10 = power(expr, 10)
  // double x = expr();
  // double y = x;
  // double y = y * y;
  // double y = y * y * x;
  // this.pow10_10 = (y * y);

  val pow10_10_2 = power(10, 10)
  // double y = 10.0D;
  // double y = y * y;
  // double y = y * y * 10.0D;
  // this.pow10_10_2 = (y * y);
}