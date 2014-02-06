package jp.seraphr.sandbox.continuation

object ContTest {
  import Cont._

  def main(args: Array[String]): Unit = {
    val tProduct = reset {
      product(List(2, 3, 0, 4, 5, 6))
    }

    println(tProduct)
  }

  def product(aList: List[Int]): Cont[Int, Int] = {
    println(aList)

    shift {
      k =>
        aList match {
          case 0 :: tail => 0
          case h :: tail => product(tail).run(a => k(h * a))
          case Nil       => k(1)
        }

    }
  }

}