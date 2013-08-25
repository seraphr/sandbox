package jp.seraphr.sandbox.calcDataAbstraction

object CalcMain {
  object IntAddSystem extends IntValueCalcSystem
  object DoubleAddSystem extends DoubleValueCalcSystem
  object IntDoubleAddSystem extends IntValueCalcSystem with DoubleValueCalcSystem

  def main(args: Array[String]): Unit = {
    IntAddTest
    println
    DoubleAddTest
    println
    IntDoubleTest
  }

  def typed[T](a: T) = a

  def IntAddTest = {
    println("IntAddable")

    val tIntAddCalculator = IntAddSystem.IntAddCalculator

    val tAddIntValues = tIntAddCalculator.calc(10, 12)
    typed[Int](tAddIntValues)
    println(s"10 + 12 = ${tAddIntValues}")

    val tAddDoubleValues = tIntAddCalculator.calc(10.5, 12.5)
    typed[Int](tAddDoubleValues)
    println(s"10.5 + 12.5 = ${tAddDoubleValues}")
  }

  def DoubleAddTest = {
    println("DoubleAddable")
    val tDoubleAddCalculator = DoubleAddSystem.DoubleAddCalculator

    val tAddIntValues = tDoubleAddCalculator.calc(10, 12)
    typed[Double](tAddIntValues)
    println(s"10 + 12 = ${tAddIntValues}")

    val tAddDoubleValues = tDoubleAddCalculator.calc(10.5, 12.5)
    typed[Double](tAddDoubleValues)
    println(s"10.5 + 12.5 = ${tAddDoubleValues}")
  }

  def IntDoubleTest{
    println("IntDoubleAddable")

    val tIntAddCalculator = IntDoubleAddSystem.IntAddCalculator

    val tAddIntValuesAsInt = tIntAddCalculator.calc(10, 12)
    typed[Int](tAddIntValuesAsInt)
    println(s"10 + 12 = ${tAddIntValuesAsInt}")

    val tAddDoubleValuesAsInt = tIntAddCalculator.calc(10.5, 12.5)
    typed[Int](tAddDoubleValuesAsInt)
    println(s"10.5 + 12.5 = ${tAddDoubleValuesAsInt}")



    val tDoubleAddCalculator = DoubleAddSystem.DoubleAddCalculator

    val tAddIntValuesAsDouble = tDoubleAddCalculator.calc(10, 12)
    typed[Double](tAddIntValuesAsDouble)
    println(s"10 + 12 = ${tAddIntValuesAsDouble}")

    val tAddDoubleValuesAsDouble = tDoubleAddCalculator.calc(10.5, 12.5)
    typed[Double](tAddDoubleValuesAsDouble)
    println(s"10.5 + 12.5 = ${tAddDoubleValuesAsDouble}")

  }

}