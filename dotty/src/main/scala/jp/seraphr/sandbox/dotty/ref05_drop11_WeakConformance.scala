package jp.seraphr.sandbox.dotty

object WeakConformance {
  // javaのprimitiveな数値型に関しての変換規則にあった、WeakConformanceを除去
  // 代わりに、変換しても情報の欠落がないときだけ、型を拡張する

  inline val b = 33
  def f(): Int = b + 1
  List(b, 33, 'a')      : List[Int]
  List(b, 33, 'a', f()) : List[Int]
  List(1.0f, 'a', 0)    : List[Float]
  List(1.0f, 1L)        : List[Double]
  List(1.0f, 1L, f())   : List[AnyVal] // fは定数では無いので、情報欠落が無いことを保証できない
  List(1.0f, 1234567890): List[AnyVal] // 1234567890 は Floatでは表現出来ない
}