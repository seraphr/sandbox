package jp.seraphr.sandbox.dataflow

import javax.annotation.processing.Processor

trait Flow[_Input] {
  def sink: Sink[_Input]
}

case class StartFlow[F, T](source: Source[F], proc: Process[F, T], next: Flow[T]) extends Flow[F] {
  override def sink = ???

  def runFlow(): Unit = {
    proc.run(source, next.sink)
  }
}

case class DefaultFlow[F, T](proc: Process[F, T], next: Flow[T]) extends Flow[F] {
  override def sink: Sink[F] = new DefaultSink[F]({
    aSource: Source[F] =>
      proc.run(aSource, next.sink)
  })
}

case class LastFlow[F](sink: MemorySink[F]) extends Flow[F]

trait FlowPoint[F, T] {
  val source: Source[F]
  val proc: Process[F, T]
  val sink: Sink[T]
}