package jp.seraphr.sandbox.dotty

object InspectTastyFile {
  import scala.tasty._
  import scala.tasty.file._

  class Consumer extends TastyConsumer {
    final def apply(reflect: Reflection)(root: reflect.Tree): Unit = {
      import reflect._
      println(root.showCode)
    }
  }
}

object InspectTastyFileMain {
  // Windows では、ファイルパスの取り扱いに失敗するらしく実行時エラー
  def main(args: Array[String]): Unit = {
    import scala.tasty.file._
    import InspectTastyFile.Consumer
    ConsumeTasty("", List("jp.seraphr.sandbox.dotty.Hoge"), new Consumer)

    // 以下がprintされる
    // package sandbox.dotty {
    //   object Hoge {
    //     val fuga: scala.Int = 1
    //   }
    // }
    
  }
}

