package jp.seraphr.sandbox.dotty

object DependentFunctionTypes {
  trait Container {
    type Elem
    val get: Elem
  }

  def getElem(c: Container): c.Elem = c.get
  val getElemF: (c: Container) => c.Elem = _.get

  case class StringC(get: String) extends Container {
    type Elem = String
  }

  getElem(StringC("hoge")): String
  getElemF(StringC("fuga")): String
}