package jp.seraphr.sandbox.dotty

// DelayedInit消える
// よって App class も、旧Application class 相当のものになる（コマンドライン引数にアクセス出来ない & JIT効かない）
// 普通に main メソッド定義しような！

object DelayedInit