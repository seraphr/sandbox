package jp.seraphr.sandbox.free

import scala.annotation.tailrec

object Trampoline {
  type Trampoline[+A] = FreeM[Function0, A]
  type More[+A] = Free[Function0, A]
  type Done[+A] = Pure[Function0, A]
  
  val More = Free
  val Done = Pure

  @tailrec
  def run[A](t: Trampoline[A]): A = t.resume match {
    case Right(v) => v
    case Left(k)  => run(k())
  }
}