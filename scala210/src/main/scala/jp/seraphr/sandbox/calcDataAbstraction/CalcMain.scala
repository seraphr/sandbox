package jp.seraphr.sandbox.calcDataAbstraction

object CalcMain {
  object IntAddSystem extends IntValueCalcSystem
  object DoubleAddSystem extends DoubleValueCalcSystem
  object IntDoubleAddSystem extends IntValueCalcSystem with DoubleValueCalcSystem with AnyShowCalcSystem

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
    import IntAddSystem._

    val tIntAddCalculator = IntAddSystem.IntAddCalculator

    val tAddIntValues = IntAddCalculator.create((10, 12)).get
    typed[Int](tAddIntValues)
    println(s"10 + 12 = ${tAddIntValues}")

    val tAddDoubleValues = IntAddCalculator.create((10.5, 12.5)).get
    typed[Int](tAddDoubleValues)
    println(s"10.5 + 12.5 = ${tAddDoubleValues}")
  }

  def DoubleAddTest = {
    println("DoubleAddable")
    import DoubleAddSystem._
    
    val tAddIntValues = system[DoubleAdd].create((10, 12)).get
    typed[Double](tAddIntValues)
    println(s"10 + 12 = ${tAddIntValues}")

    val tAddDoubleValues = system[DoubleAdd].create((10.5, 12.5)).get
    typed[Double](tAddDoubleValues)
    println(s"10.5 + 12.5 = ${tAddDoubleValues}")
  }

  def IntDoubleTest {
    println("IntDoubleAddable")

    import IntDoubleAddSystem._

    val tAddIntValuesAsInt = IntAddCalculator.create((10, 12)).get
    typed[Int](tAddIntValuesAsInt)
    println(s"10 + 12 = ${tAddIntValuesAsInt}")

    val tAddDoubleValuesAsInt = IntAddCalculator.create((10.5, 12.5)).get
    typed[Int](tAddDoubleValuesAsInt)
    println(s"10.5 + 12.5 = ${tAddDoubleValuesAsInt}")

    val tAddIntValuesAsDouble = system[DoubleAdd].create((10, 12)).get
    typed[Double](tAddIntValuesAsDouble)
    println(s"10 + 12 = ${tAddIntValuesAsDouble}")

    val tAddDoubleValuesAsDouble = system[DoubleAdd].create((10.5, 12.5)).get
    typed[Double](tAddDoubleValuesAsDouble)
    println(s"10.5 + 12.5 = ${tAddDoubleValuesAsDouble}")

    val tAddStringValuesAsDouble = ShowCalculator.create(List(1, 2, 3)).get
    typed[String](tAddStringValuesAsDouble)
    println(s"List(1,2,3) = ${tAddStringValuesAsDouble}")
  }

}