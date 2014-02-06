package jp.seraphr.sandbox.continuation

case class Cont[R, A](run: (A => R) => R) {
  def flatMap[B](f: A => Cont[R, B]): Cont[R, B] = Cont {
    newRun => this.run(a => f(a).run(newRun))
  }

  def map[B](f: A => B): Cont[R, B] = flatMap {
    a =>
      Cont {
        newRun => newRun(f(a))
      }
  }
}

object Cont {
  def shift[R, A](k: (A => R) => R): Cont[R, A] = Cont(k)

  def reset[R](c: => Cont[R, R]): R = c.run(identity)
}