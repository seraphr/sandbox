package jp.seraphr.sandbox.calcDataAbstraction

trait IntValueCalcSystem extends IntAddCalcSystem {
  implicit object IntIntAddable extends IntAddCalculator.Calcable[(Int, Int)] {
    override def asInt(a: (Int, Int)) = a
  }

  implicit object DoubleIntAddable extends IntAddCalculator.Calcable[(Double, Double)] {
    override def asInt(a: (Double, Double)) = (a._1.intValue(), a._2.intValue())
  }
}

trait DoubleValueCalcSystem extends DoubleAddCalcSystem {
  implicit object IntDoubleAddable extends DoubleAddCalculator.DoubleAddable[(Int, Int)] {
    override def asDouble(a: (Int, Int)) = (a._1.doubleValue(), a._2.doubleValue)
  }

  implicit object DoubleDoubleAddable extends DoubleAddCalculator.DoubleAddable[(Double, Double)] {
    override def asDouble(a: (Double, Double)) = a
  }
}

trait AnyShowCalcSystem extends ShowCalcSystem {
  implicit object AnyShowable extends ShowCalculator.Showable[Any] {
    override def as(a: Any) = a.toString()
  }
}