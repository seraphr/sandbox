package jp.seraphr.sandbox.dotty

// abstract type に対する type projectionを禁止
// unsound らしい。 
// https://github.com/lampepfl/dotty/issues/1050
object GeneralTypeProjection {
  type A = {type X}
  trait B {type X}
  class C {type X}
  type D = C

  trait Hoge[AA <: A, BB <: B, CC <: C, DD <: D] {
    type AX = A#X
    type BX = B#X
    type CX = C#X
    type DX = D#X

    // 以下は全てダメ scala2.12では全部OK
    //type AAX = AA#X
    //type BBX = BB#X
    //type CCX = CC#X
    //type DDX = DD#X
  }
}