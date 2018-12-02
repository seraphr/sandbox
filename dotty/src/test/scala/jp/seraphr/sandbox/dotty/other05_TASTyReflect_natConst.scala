package jp.seraphr.sandbox.dotty


object NatConst {
  val one = TastyReflect.natConst(1)

  // コンパイルエラー Parameter must be natural number
  // val error = TastyReflect.natConst(0)
}

// 以下のような感じのコードになる
// public final class NatConst$
// {
//   private final int one;
//   public int one()
//   {
//     return this.one;
//   }
//   public NatConst$()
//   {
//     this.one = 1;
//   }
// }