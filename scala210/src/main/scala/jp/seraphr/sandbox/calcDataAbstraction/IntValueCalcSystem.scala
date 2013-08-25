package jp.seraphr.sandbox.calcDataAbstraction

trait IntValueCalcSystem extends IntAddCalcSystem {
  implicit object IntIntAddable extends IntAddCalculator.Calcable[Int] {
    override def asInt(a: Int) = a
  }

  implicit object DoubleIntAddable extends IntAddCalculator.Calcable[Double] {
    override def asInt(a: Double) = a.intValue()
  }
}

trait DoubleValueCalcSystem extends DoubleAddCalcSystem {
  implicit object IntDoubleAddable extends DoubleAddCalculator.DoubleAddable[Int] {
    override def asDouble(a: Int) = a.doubleValue()
  }

  implicit object DoubleDoubleAddable extends DoubleAddCalculator.DoubleAddable[Double] {
    override def asDouble(a: Double) = a
  }
}