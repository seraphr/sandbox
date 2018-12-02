package jp.seraphr.sandbox.dotty

object TraitParameters {
  trait Greeting(val name: String) {
    def msg = s"How are you, $name"
  }

  class C(val s: String) extends Greeting(s) {
    println(msg)
  }
}

object TraitParameters2 {
  trait Functor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]
  }
  object Functor {
    def map[F[_]: Functor, A, B](fa: F[A])(f: A => B): F[B] = implicitly[Functor[F]].map(fa)(f)
  }

  trait FunctorParam[F[_]: Functor] {
    def map[A, B](fa: F[A])(f: A => B): F[B] =
      Functor.map(fa)(f)
  }
}