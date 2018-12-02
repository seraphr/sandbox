package jp.seraphr.sandbox.dotty

object StructuralTypes {
  // JavaVM以外のプラットフォームで、うまく構造的部分型が取り扱えなかった問題に対応するため
  // 構造的部分型が、Javaのリフレクションに依存したものから、より抽象的なものに分離されました。
  // implicit に Selectable が見えていないと、コンパイルエラーになります。
  import scala.reflect.Selectable.reflectiveSelectable

  // Selectableは以下のような型
  // trait Selectable extends Any {
  //   def selectDynamic(name: String): Any
  //   def selectDynamicMethod(name: String, paramClasses: ClassTag[_]*): Any =
  //     new UnsupportedOperationException("selectDynamicMethod")
  // }

  type HasClose = {
    def close(): Unit
  }

  class Hoge {
    def close(): Unit = ()
  }

  val tHoge: HasClose = new Hoge
  tHoge.close()
}