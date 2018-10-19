package jp.seraphr.sandbox.dotty

object AutoParameterTupling {
  val tList = List((1,2), (2,3), (3,4))

  val tOld = tList.map {
    case (l, r) => l + r
  }

  // scala 2ではコンパイルエラー
  val tNew = tList.map {
    (l, r) => l + r
  }
  val tNew2 = tList.map(_ + _)
  // val f: (Int, Int) => Int = _ + _
  // tList.map(f)
}