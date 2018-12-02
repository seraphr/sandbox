package jp.seraphr.sandbox.dotty

object IntersectionTypes {
  trait A
  trait B
  
  // with も残るが、deprecatedになって、そのうち消える
  def withAB(ab: A with B): Unit = ()
  def withBA(ba: B with A): Unit = ()
  def andAB(ab: A & B): Unit = ()
  def andBA(ba: A & B): Unit = ()
}