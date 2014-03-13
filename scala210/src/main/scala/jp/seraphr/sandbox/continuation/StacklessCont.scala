package jp.seraphr.sandbox.continuation

import jp.seraphr.sandbox.free.Trampoline._
import jp.seraphr.sandbox.free.Trampoline

/**
 */
case class StacklessCont[R, +A](run: (A => R) => Trampoline[R]) {
  import Trampoline.{ run => trun }

  def flatMap[B](f: A => StacklessCont[R, B]): StacklessCont[R, B] = StacklessCont {
    newRun =>
      More(() => this.run(a => trun(f(a).run(newRun))))
  }

  def map[B](f: A => B): StacklessCont[R, B] = StacklessCont {
    k =>
      More(() => this.run(a => k(f(a))))
  }
}

object StacklessCont {
  def shift[R, A](k: (A => R) => Trampoline[R]): StacklessCont[R, A] = StacklessCont(k)

  def reset[R](c: => StacklessCont[R, R]): R = Trampoline.run(c.run(a => a))
}