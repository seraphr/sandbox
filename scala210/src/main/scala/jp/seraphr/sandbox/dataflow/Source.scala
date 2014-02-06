package jp.seraphr.sandbox.dataflow

trait Source[F] {
  val metaData: String
  def read: Option[F]
  val asIterator = Iterator.continually(read).takeWhile(_.isDefined).map(_.get)
}

class DefaultSource[F](aIterator: Iterator[F], aMeta: String) extends Source[F] {
  val metaData: String = aMeta
  def read: Option[F] =
    if (aIterator.hasNext)
      Some(aIterator.next)
    else
      None
}