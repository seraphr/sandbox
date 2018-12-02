package jp.seraphr.sandbox.dotty

object ClassShadowing {
  // super type内で定義されているのと同名の型を定義できなくなりました

  trait Super {
    class A
  }

  trait Sub extends Super {
    // class A
  }
}