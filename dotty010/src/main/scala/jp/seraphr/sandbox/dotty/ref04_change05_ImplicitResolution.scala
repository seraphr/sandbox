package jp.seraphr.sandbox.dotty

object ImplicitResolution {
  // メンバの implicit val には、型を明示しないといけない
  implicit val hoge: Int = 10

  // スコープのネストが、探索順位に影響するようになった
  // scala2.12 ではコンパイルエラー
  trait Z
  def f(implicit i: Z) = {
    def g(implicit j: Z) = {
      implicitly[Z]
    }
  }

  // 再帰的なimplicit 探索で、implicitな候補が複数見つかって失敗した時に、その失敗が伝播するようになった
  // 以下の`implicitly[C]`は scala 2.12ではコンパイルが通る（def cが選ばれる）が、dottyではエラー
  trait I {
    class A
    class B extends C
    class C
    implicit def a1: A
    implicit def a2: A
    implicit def b(implicit a: A): B
    implicit def c: C

    // implicitly[C]

    // 上記の影響で、「Aが失敗したらBが成功する」というのが実現できなくなったためか、Notが導入
    // らしいが、scala2でうまく行って、dottyでうまく行かない、具体例が作れなかった…
    implicitly[scala.implicits.Not[C]]
  }

  // implicit searchが発散した場合（implicitの展開が再帰的に行われてしまうような場合など）の失敗が、通常の探索の失敗と同じ扱いになった
  // = 他の候補の探索を継続する
  // scala 2では発散した場合（殆どの場合）失敗していた
  // らしいが、scala2でうまく行いかなくて、dottyでうまく行く、具体例が作れなかった…
}