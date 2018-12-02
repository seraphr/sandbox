package jp.seraphr.sandbox.dotty

object ErasedTerms {
  trait ON
  trait OFF

  case class Switch[S]() {
    def on(implicit erased ev: =:=[S, OFF]) = Switch[ON]()
    def off(implicit erased ev: S =:= ON) = Switch[OFF]()
  }

  Switch[OFF]()
    .on
    .off
    
  // コンパイル結果は以下のように evが消えている
  // ErasedTerms.Switch..MODULE$.apply().on().off();

  // erased 無しだと
  // ErasedTerms.Switch..MODULE$.apply()
  // .on(Predef..eq.colon.eq..MODULE$.tpEquals())
  // .off(Predef..eq.colon.eq..MODULE$.tpEquals());

}