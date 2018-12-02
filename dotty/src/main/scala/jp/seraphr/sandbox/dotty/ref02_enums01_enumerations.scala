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
    // enumValues などの便利メソッドが生えている
    Enums.Color.enumValues.foreach { e =>
      println(s"enum: ${e}, tag: ${e.enumTag}")
    }
    // enum: Red, tag: 0
    // enum: Green, tag: 1
    // enum: Blue, tag: 2

    
    Enums.Color.enumValue.foreach { (i, e) =>
      println(s"i: ${i}, enum: ${e}, tag: ${e.enumTag}")
    }
    // i: 0, enum: Red, tag: 0
    // i: 1, enum: Green, tag: 1
    // i: 2, enum: Blue, tag: 2


    Enums.Color.enumValueNamed.foreach { (n, e) =>
      println(s"name: ${n}, enum: ${e}, tag: ${e.enumTag}")
    }
    // name: Red, enum: Red, tag: 0
    // name: Green, enum: Green, tag: 1
    // name: Blue, enum: Blue, tag: 2

  }
}