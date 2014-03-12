package jp.seraphr.sandbox.free

import language.higherKinds
import jp.seraphr.sandbox.continuation.Cont

trait Functor[F[_]] {
  def map[A, B](m: F[A])(f: A => B): F[B]
}

object Functor {
  implicit def ContIsFunctor[R] = new Functor[({ type F[A] = Cont[R, A] })#F] {
    type F[X] = ({ type F[A] = Cont[R, A] })#F[X]

    override def map[A, B](m: F[A])(f: A => B): F[B] = m.map(f)
  }
}

object FreeM {
  private case class FlatMap[S[+_], A, +B](a: FreeM[S, A], f: A => FreeM[S, B]) extends FreeM[S, B]
}

// data Free f a = Pure a | Free (f (Free f a))
sealed trait FreeM[S[+_], +A] {
  import FreeM.FlatMap

  def flatMap[B](f: A => FreeM[S, B]): FreeM[S, B] = this match {
    case a FlatMap g =>
      FlatMap(a, (x: Any) => g(x) flatMap f)
    case x => FlatMap(x, f)
  }
  def map[B](f: A => B): FreeM[S, B] = flatMap(a => Pure(f(a)))

  final def resume(implicit functor: Functor[S]): Either[S[FreeM[S, A]], A] =
    this match {
      case Pure(a) => Right(a)
      case Free(k) => Left(k)
      case FlatMap(a, f) => a match {
        case Pure(a)       => f(a).resume
        case Free(k)       => Left(functor.map(k)(_ flatMap f))
        case FlatMap(b, g) => b.flatMap((x: Any) => g(x) flatMap f).resume
      }
    }
}

case class Pure[S[+_], +A](a: A) extends FreeM[S, A]
case class Free[S[+_], +A](k: S[FreeM[S, A]]) extends FreeM[S, A]