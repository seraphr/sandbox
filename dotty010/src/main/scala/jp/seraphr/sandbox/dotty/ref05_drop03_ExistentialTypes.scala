package jp.seraphr.sandb

// 作用なら forSome

object ExistentialTypes {
  // _ のみになる
  type A = List[_]

  // 一応 forSomeがなくなるとできなくなることはあるが、まず困らない
  // 以下は、Scala 2.12のコードだが、 これの T1が定義できなくなる
  // trait C[A] {
  //   def accept(a: A): Unit = ()
  // }
  // case object C1 extends C[Int]
  
  // type T1 = C[C[A]] forSome { type A }
  // type T2 = C[C[A] forSome { type A }] // == C[C[_]]
  
  // def t1: Unit = {
  //   val v: T1 = ???
  //   v.accept(C1) // type mismatch;  found: C1.type  required: C[A]
  // }
  
  // def t2: Unit = {
  //   val v: T2 = ???
  //   v.accept(C1) // OK
  // }
}