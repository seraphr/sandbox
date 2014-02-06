package jp.seraphr.sandbox.dataflow

trait Process[F, T] {
  def run(aSource: Source[F], aSink: Sink[T]): Unit
}

class IntStringProcess extends Process[Int, String] {
  def run(aSource: Source[Int], aSink: Sink[String]): Unit = {
    println("IntString")

    aSink.open(aSource.metaData + " toString!!")
    aSource.asIterator.foreach(a => aSink.write(a.toString))
    aSink.close
  }
}

class DoubleProcess extends Process[Int, Int] {
  def run(aSource: Source[Int], aSink: Sink[Int]): Unit = {
    println("Double")
    aSink.open(aSource.metaData + " double!")
    aSource.asIterator.foreach(a => aSink.write(a * 2))
    aSink.close
  }
}