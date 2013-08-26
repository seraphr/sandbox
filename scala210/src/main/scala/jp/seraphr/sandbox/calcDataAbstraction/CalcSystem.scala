package jp.seraphr.sandbox.calcDataAbstraction

trait CalcSystem {
  trait Calculator {
    type Calcable[_]
    type CalcResult

    def create[_T: Calcable](a: _T): Result
    trait Result {
      def get: CalcResult
    }
  }

  def system[_T <: Calculator](implicit ev: _T): Calculator = implicitly[Calculator]
}

trait ShowCalcSystem extends CalcSystem {
  type Show = ShowCalculator.type
  
  implicit object ShowCalculator extends Calculator {
    trait Showable[-_T] {
      def as(a: _T): String
    }

    type Calcable[X] = Showable[X]
    type CalcResult = String

    override def create[_T: Calcable](a: _T): Result = new Result {
      override val get: CalcResult = {
        val tAddable = implicitly[Calcable[_T]]
        tAddable.as(a)
      }
    }
  }
}

trait IntAddCalcSystem extends CalcSystem {
  type IntAdd = IntAddCalculator.type
  
  implicit object IntAddCalculator extends Calculator {
    trait IntAddable[-_T] {
      def asInt(a: _T): (Int, Int)
    }

    type Calcable[X] = IntAddable[X]
    type CalcResult = Int

    override def create[_T: Calcable](a: _T): Result = new Result {
      override val get: CalcResult = {
        val tAddable = implicitly[Calcable[_T]]
        val (tLeft, tRight) = tAddable.asInt(a)

        tLeft + tRight
      }
    }
  }
}

trait DoubleAddCalcSystem extends CalcSystem {
  type DoubleAdd = DoubleAddCalculator.type
  
  implicit object DoubleAddCalculator extends Calculator {
    trait DoubleAddable[-_T] {
      def asDouble(a: _T): (Double, Double)
    }

    type Calcable[X] = DoubleAddable[X]
    type CalcResult = Double

    override def create[_T: Calcable](a: _T): Result = new Result {
      override val get: CalcResult = {
        val tAddable = implicitly[Calcable[_T]]
        val (tLeft, tRight) = tAddable.asDouble(a)

        tLeft + tRight
      }
    }
  }
}