package jp.seraphr.sandbox.dotty


object VarargPatterns {
  
  List(1,2,3,4) match {
    // case List(1,2, _*) => 
    // case List(1,2, xs @ _*) => 
    case List(1,2, _: _*) => 
    case List(2,3, xs: _*) => 
    case _ =>
  }

  // unapplyで unapplySeqと同じことができるようになった
  class Person(val name: String, val children: Person *)
  object Person {
    def unapply(p: Person) = Some((p.name, p.children))
    // def unapplySeq(p: Person) = Some((p.name, p.children))
  }
  def childCount(p: Person) = p match {
    case Person(_, ns : _*) => ns.length
  }
}