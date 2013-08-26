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
//  implicit def IntDoubleAddable(implicit ev: DoubleAddCalculator): ev.DoubleAddable[(Int, Int)] = new ev.DoubleAddable[(Int, Int)]{
//    override def asDouble(a: (Int, Int)) = (a._1.doubleValue(), a._2.doubleValue)
//  }
  
  implicit object IntDoubleAddable extends DoubleAddable[(Int, Int)] {
    override def asDouble(a: (Int, Int)) = (a._1.doubleValue(), a._2.doubleValue)
  }

//  implicit def DoubleDoubleAddable(implicit ev: DoubleAddCalculator): ev.DoubleAddable[(Double, Double)] = new ev.DoubleAddable[(Double, Double)]{
//    override def asDouble(a: (Double, Double)) = a
//  }
  
  implicit object DoubleDoubleAddable extends DoubleAddable[(Double, Double)] {
    override def asDouble(a: (Double, Double)) = a
  }
}

trait AnyShowCalcSystem extends ShowCalcSystem {
  implicit object AnyShowable extends ShowCalculator.Showable[Any] {
    override def as(a: Any) = a.toString()
  }
}