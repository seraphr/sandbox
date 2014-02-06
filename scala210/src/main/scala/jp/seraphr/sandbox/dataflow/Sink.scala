package jp.seraphr.sandbox.dataflow

import scala.collection.mutable.ListBuffer

trait Sink[T] {
  def open(aMeta: String): Unit
  def write(aData: T): Unit
  def close(): Unit
}

class DefaultSink[T](aCallback: Source[T] => Unit) extends Sink[T] {
  private val mBuffer = ListBuffer[T]()
  private var mMeta: String = _

  def open(aMeta: String): Unit = {
    mMeta = aMeta
  }
  def write(aData: T): Unit = mBuffer += aData
  def close(): Unit = {
    aCallback(new DefaultSource(mBuffer.toList.iterator, mMeta))
  }
}

class MemorySink[T] extends Sink[T] {
  import scala.concurrent._

  private val mPromise = promise[(String, List[T])]

  private val mBuffer = ListBuffer[T]()
  private var mMeta: String = _

  def open(aMeta: String): Unit = {
    mMeta = aMeta
  }
  def write(aData: T): Unit = mBuffer += aData
  def close(): Unit = {
    mPromise.success((mMeta, mBuffer.toList))
  }

  val result = mPromise.future
}