package jp.seraphr.sandbox.dotty

object TypeLambdas {
  type Map0[K, V] = Map[K, V]
  type Map1 = [K, V] => Map[K, V]
  type Map2[K] = [V] => Map[K, V]
  type Map3 = [K] => [V] => Map[K, V]
}

object RunState {
  def main(args: Array[String]): Unit = {
    val tState: State.St[String][Int] = State {s => (s, 1)}
    val tInt = Functor.map(tState)(_ + 1).run("")._2
    println(tInt) // 2
  }
}

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

object Functor {
  def map[F[_]: Functor, A, B](fa: F[A])(f: A => B): F[B] = implicitly[Functor[F]].map(fa)(f)
}

case class State[S, A](run: S => (S, A))
object State {
  type St[S] = [A] => State[S, A]
  implicit def stateFunctor[S]: Functor[St[S]] = new Functor[St[S]] {
    def map[A, B](fa: St[S][A])(f: A => B): St[S][B] = State { s0 =>
      val (s1, a) = fa.run(s0)
      (s1, f(a))
    }
  }
}