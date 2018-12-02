package jp.seraphr.sandbox.dotty

object Enums {
  enum Color {
    case Red, Green, Blue
  }

  // 大体以下と同じ
  sealed trait Color2
  object Color2 {
    case object Red extends Color2
    case object Green extends Color2
    case object Blue extends Color2
  }
}

object RunEnums {
  def main(args: Array[String]): Unit = {
    Enums.Color.enumValues.foreach { e =>
      println(s"enum: ${e}, tag: ${e.enumTag}")
    }
    // enum: Red, tag: 0
    // enum: Green, tag: 1
    // enum: Blue, tag: 2
  }
}