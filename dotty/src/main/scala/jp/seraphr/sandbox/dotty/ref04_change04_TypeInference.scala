package jp.seraphr.sandbox.dotty

object TypeInference {
  // ドキュメントが空っぽなので、謎
  // 把握している限りいくつか

  // method independent typeが、同じ引数リスト内で使えるようになった
  object MethodIndependentType {
    trait A {
      type T
    }

    object B extends A {type T = String}
    object C extends A {type T = Int}

    def method(a: A, t: a.T): Unit = ()
    method(B, "")
    method(C, 1)
  }

  // 同じ引数リスト内で、前の引数の推論結果を使用可能になった
  object SameArgList {
    def foldLeft[A, B](c: List[A], zero: B, f: (B, A) => B): B = ???

    // scala2.12 ではコンパイルエラー missing parameter type
    foldLeft(List(1), "", _ + _)
  }

  // 別の後ろの引数リストでの型推論結果が、前の引数リストにフィードバックされるようになった
  object LubDiffArgList {
    def fold[A, B](o: Option[A])(zero: B)(f: A => B): B = ???

   // scala2.12 ではコンパイルエラー error: type mismatch
    val list = fold(Option(1))(Nil)(List(_))
  }

  // さようなら AUXパターン
  object ByeByeAuxPattern {
    trait HasSize[A] {
      type Size
      def size(a:A): Size
    }

    trait Monoid[A] {
      val zero: A
      def append(l: A, r: A): A
    }

    // scala2.12 ではコンパイルエラー error: illegal dependent method type
    def method[A](a1: A, a2: A)(implicit S: HasSize[A], M: Monoid[S.Size]): S.Size =
      M.append(S.size(a1), S.size(a2))
  }
}