package jp.seraphr.sandbox.dotty

object OpaqueTypeAliases {
  // opaque type は、コンパニオンオブジェクト内でのみ、同じ型とみなされる
  opaque type UserId = String
  object UserId {
    def apply(base: String): UserId = base

    implicit class UserIdOps(id: UserId) {
      def asString: String = id
    }
  }

  opaque type ItemId = String
  object ItemId {
    def apply(base: String): ItemId = base

    implicit class UserIdOps(id: ItemId) {
      def asString: String = id
    }
  }
}

object UserOpaqueType {
  import OpaqueTypeAliases._
  val tString = "string"
  val tUserId = UserId("user1")
  val tItemId = ItemId("item1")

  // コメントアウトしているものはコンパイルエラー
  val tId1: UserId = tUserId
  // val tId2: String = tUserId
  // val tId3: ItemId = tUserId
  // val tId4: UserId = tItemId
  // val tId5: String = tItemId
  val tId6: ItemId = tItemId
  // val tId7: UserId = tString
  val tId8: String = tString
  // val tId9: ItemId = tString
  val tId10: String = tUserId.asString
  val tId11: String = tItemId.asString
}
