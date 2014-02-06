package jp.seraphr.sandbox.dataflow

import scala.concurrent.ExecutionContext
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object DataFlowMain {

  def main(args: Array[String]): Unit = {
    import ExecutionContext.Implicits.global

    val tStartSource = new DefaultSource(Iterator(1, 2, 3, 4, 5), "meta!!")
    val tMemorySink = new MemorySink[String]

    lazy val tDoubleFlow1 = StartFlow(tStartSource, new DoubleProcess, tDoubleFlow2)
    lazy val tDoubleFlow2 = DefaultFlow(new DoubleProcess, tToStringFlow)
    lazy val tToStringFlow = DefaultFlow(new IntStringProcess, tLastFlow)
    lazy val tLastFlow = LastFlow(tMemorySink)

    tMemorySink.result.onSuccess {
      case (meta, list) =>
        println(s"meta is ${meta}")
        println(list.reduce(_ + _))
    }

    println("start")
    tDoubleFlow1.runFlow

    Await.ready(tMemorySink.result, Duration.Inf)
    println("おわた")
  }

}