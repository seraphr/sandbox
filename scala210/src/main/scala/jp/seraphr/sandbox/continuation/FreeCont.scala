package jp.seraphr.sandbox.continuation

import jp.seraphr.sandbox.free.FreeM
import jp.seraphr.sandbox.free.Free
import jp.seraphr.sandbox.free.Pure
import scala.annotation.tailrec

object FreeCont {
  type _Cont[R, A] = FreeM[({ type F[+X] = Cont[R, X] })#F, A]
  type _Pure[R, A] = Pure[({ type F[+X] = Cont[R, X] })#F, A]
  type _Free[R, A] = Free[({ type F[+X] = Cont[R, X] })#F, A]

  def shift[R, A](run: (A => R) => R): _Cont[R, A] = {
    val tCont = Cont(run).map(new _Pure[R, A](_))
    new _Free[R, A](tCont)
  }

  /**
   * ここを末尾再帰に出来ないと、Stacklessにならない…
   */
  def reset[R](cont: _Cont[R, R]): R = cont.resume match {
    case Right(a)        => a
    case Left(Cont(run)) => run(x => reset(x))
  }
}

case class FreeCont[R, +A](run: (A => R) => R)