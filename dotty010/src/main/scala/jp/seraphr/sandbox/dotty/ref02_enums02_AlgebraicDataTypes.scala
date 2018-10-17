package jp.seraphr.sandbox.dotty

// 代数的データ型
object AlgebraicDataTypes {
  enum Option[+T] {
    case Some(x: T)
    case None
  }

  // 大体以下と同じ
  trait Option2[+T]
  object Option2 {
    case class Some[T](t: T) extends Option2[T]
    case object None extends Option2[Nothing]
  }
}

