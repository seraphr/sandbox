package jp.seraphr.sandbox.union

import scala.language.higherKinds

@annotation.implicitNotFound("${X} should equal to ${A} or ${B}")
trait UnionType[A[_], B[_], X]

trait UnionTypeBase {
  type ConstantType[X] = { type T[A] = X }
  type CT[X] = ConstantType[X]
  type Acceptable[A[_], X] = UnionType[A, A, X]
  type SimpleType[A] = { type T[X] = Acceptable[CT[A]#T, X] }
  type U1[A] = { type U[X] = Acceptable[CT[A]#T, X] }
  type U2[A, B] = { type U[X] = UnionType[SimpleType[A]#T, SimpleType[B]#T, X] }
  type U3[A, B, C] = { type U[X] = UnionType[SimpleType[A]#T, U2[B, C]#U, X] }
  type U4[A, B, C, D] = { type U[X] = UnionType[U2[A, B]#U, U2[C, D]#U, X] }

  val genericInstance = new UnionType[Any, Any, Any] {}
}

trait UnionTypeWeakInstance extends UnionTypeBase {
  implicit def Instance1[A[_], B[_], C](implicit ev: Acceptable[A, C]) = genericInstance.asInstanceOf[UnionType[A, B, C]]
  implicit def Instance2[A[_], B[_], C](implicit ev: Acceptable[B, C]) = genericInstance.asInstanceOf[UnionType[A, B, C]]
}

trait UnionTypeStrongInstance extends UnionTypeWeakInstance {
  implicit def Instance3[A, B, C](implicit ev1: A =:= B, ev2: A =:= C) = genericInstance.asInstanceOf[UnionType[CT[A]#T, CT[B]#T, C]]
}

object UnionType extends UnionTypeStrongInstance
