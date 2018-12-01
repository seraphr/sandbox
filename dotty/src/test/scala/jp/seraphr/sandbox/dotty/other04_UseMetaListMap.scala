package jp.seraphr.sandbox.dotty

object UseMetaListMap {
  def baseList = List(1,2,3)
  def mappedList = MetaListMap.map(baseList)(_ + 2)

  def main(args: Array[String]): Unit = {
    println(mappedList)
    // List(3, 4, 5)
  }
}

// mapがループで構成されている
//  public scala.collection.immutable.List<java.lang.Object> mappedList();
//     Code:
//        0: aload_0
//        1: invokevirtual #47                 // Method baseList:()Lscala/collection/immutable/List;
//        4: astore_1
//        5: aload_0
//        6: invokedynamic #64,  0             // InvokeDynamic #0:apply$mcII$sp:(Ljp/seraphr/sandbox/dotty/UseMetaListMap$;)Lscala/compat/java8/JFunction1$mcII$sp;
//       11: astore_2
//       12: aload_1
//       13: astore_3
//       14: aload_3
//       15: invokeinterface #70,  1           // InterfaceMethod scala/collection/LinearSeqOptimized.length:()I
//       20: istore        4
//       22: new           #72                 // class scala/collection/mutable/ListBuffer
//       25: dup
//       26: invokespecial #73                 // Method scala/collection/mutable/ListBuffer."<init>":()V
//       29: astore        5
//       31: aload_3                           *** if(tList.nonEmpty)
//       32: invokeinterface #79,  1           // InterfaceMethod scala/collection/TraversableOnce.nonEmpty:()Z
//       37: ifeq          90                  
//       40: aload         5                   *** このあたりから、appendの引数になるArray作成
//       42: getstatic     #32                 // Field scala/Predef$.MODULE$:Lscala/Predef$;
//       45: iconst_1
//       46: newarray       int
//       48: dup
//       49: iconst_0
//       50: aload_3
//                                             *** start f(tList.head)
//       51: invokevirtual #85                 // Method scala/collection/immutable/List.head:()Ljava/lang/Object;
//       54: invokestatic  #91                 // Method scala/runtime/BoxesRunTime.unboxToInt:(Ljava/lang/Object;)I
//       57: istore        6
//       59: aload_2
//       60: iload         6
//       62: invokestatic  #95                 // Method scala/runtime/BoxesRunTime.boxToInteger:(I)Ljava/lang/Integer;
//       65: invokeinterface #100,  2          // InterfaceMethod scala/Function1.apply:(Ljava/lang/Object;)Ljava/lang/Object;
//                                             *** end f(tList.head)
//       70: invokestatic  #91                 // Method scala/runtime/BoxesRunTime.unboxToInt:(Ljava/lang/Object;)I
//       73: iastore
//       74: invokevirtual #38                 // Method scala/LowPriorityImplicits.wrapIntArray:([I)Lscala/collection/mutable/WrappedArray;
//                                             *** ここまで、appendのArray作成
//       77: invokeinterface #106,  2          // InterfaceMethod scala/collection/mutable/BufferLike.append:(Lscala/collection/Seq;)V
//       82: aload_3
//       83: invokevirtual #109                // Method scala/collection/immutable/List.tail:()Lscala/collection/immutable/List;
//       86: astore_3
//       87: goto          31                  *** ループ  
//       90: aload         5
//       92: invokevirtual #112                // Method scala/collection/mutable/ListBuffer.result:()Lscala/collection/immutable/List;
//       95: areturn