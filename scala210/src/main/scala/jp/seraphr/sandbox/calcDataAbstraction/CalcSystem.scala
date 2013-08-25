package jp.seraphr.sandbox.calcDataAbstraction

trait CalcSystem {
  trait Calculator {
    type Calcable[_]
    type CalcResult

    def calc[_T: Calcable](aLeft: _T, aRIght: _T): CalcResult
  }
}

trait IntAddCalcSystem extends CalcSystem {
  implicit object IntAddCalculator extends Calculator {
    trait IntAddable[_T] {
      def asInt(a: _T): Int
    }

    type Calcable[X] = IntAddable[X]
    type CalcResult = Int

    implicit class TypeUtil[_T: Calcable](a: _T){
      val tAddable = implicitly[Calcable[_T]]

      def asInt = tAddable.asInt(a)
    }

    override def calc[_T: Calcable](aLeft: _T, aRight: _T): CalcResult = {
        aLeft.asInt + aRight.asInt
    }
  }
}

trait DoubleAddCalcSystem extends CalcSystem {
  implicit object DoubleAddCalculator extends Calculator {
    trait DoubleAddable[_T] {
      def asDouble(a: _T): Double
    }

    type Calcable[X] = DoubleAddable[X]
    type CalcResult = Double

    implicit class TypeUtil[_T: Calcable](a: _T){
      val tAddable = implicitly[Calcable[_T]]

      def asDouble = tAddable.asDouble(a)
    }

    override def calc[_T: Calcable](aLeft: _T, aRight: _T): CalcResult = {
        aLeft.asDouble + aRight.asDouble
    }
  }
}