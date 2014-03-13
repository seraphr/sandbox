package jp.seraphr.sandbox.continuation

import scala.util.Random
import scala.annotation.tailrec
import jp.seraphr.sandbox.free.Trampoline

object StacklessContMain {
  import StacklessCont._
  
  type _Cont[R, A] = StacklessCont[R, A]

  val mRandom = new Random

  def randomList: List[Int] = {
    @tailrec
    def createList(aCurrent: List[Int], aLength: Int): List[Int] =
      if (aLength == 0)
        aCurrent
      else
        createList(mRandom.nextInt :: aCurrent, aLength - 1)

    val tLength = mRandom.nextInt(1000000)

    createList(Nil, tLength)
  }

  def main(args: Array[String]): Unit = {
    val tTimes = 100
    val tTimeSeq = (1 to tTimes).map {
      tCount =>
        val tList = randomList

        val tTimeCont = time(s"Cont-${tCount}") {
          reset {
            sum(tList)
          }
        }

        val tTimeNormal = time(s"Normal-${tCount}") {
          sumNormal(tList)
        }

        (tTimeCont, tTimeNormal)
    }

    val (tContSum, tNormalSum) = tTimeSeq.fold((0L, 0L)) {
      case ((tContAcc, tNormalAcc), (tCont, tNormal)) => (tContAcc + tCont, tNormalAcc + tNormal)
    }

    println(s"cont sum = ${tContSum}ms")
    println(s"normal sum = ${tNormalSum}ms")

//    val tList = List(2, 3, 0, 4, 5, 6)
//    val tSum1 = reset {
//      sum(tList)
//    }
//    
//    assert(tSum1 == tList.sum)
//    println(s"${tSum1} == ${tList.sum}")
  }

  def sum(aList: List[Int]): _Cont[Long, Long] = {
    def sumInner(aList: List[Int], aCurrent: _Cont[Long, Long]): _Cont[Long, Long] = aList match {
      case h :: tail => sumInner(tail, aCurrent.map(_ + h))
      case Nil       => aCurrent
    }

    sumInner(aList, shift(k => Trampoline.Done((0))))
  }

  def sumNormal(aList: List[Int]): Long = {
    @tailrec
    def productInner(aList: List[Int], aCurrent: Long): Long = aList match {
      case x :: xs => productInner(xs, x + aCurrent)
      case Nil     => aCurrent
    }

    productInner(aList, 0)
  }

  def time(aName: String)(f: => Unit): Long = {
    val tStart = System.currentTimeMillis()
    f
    val tEnd = System.currentTimeMillis()

    val tTime = tEnd - tStart
    println(s"${aName} : ${tTime}ms")
    tTime
  }
}