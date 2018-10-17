package jp.seraphr.sandbox.dotty

object ClassShadowing {
  // super type と同名の型を定義できなくなった

  trait Super {
    class A
  }

  trait Sub extends Super {
    // class A
  }
}