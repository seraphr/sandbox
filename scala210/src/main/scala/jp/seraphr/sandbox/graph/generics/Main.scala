package jp.seraphr.sandbox.graph.generics

object Main {
  class DefaultNode extends Node[DefaultNode, DefaultEdge] {
    override val graph = WeightedDirectedGraph
  }
  case class DefaultEdge(from: DefaultNode, to: DefaultNode, weight: Int)
    extends WeightedDirectedEdge[DefaultNode, DefaultEdge] {

    override val nodes = (from, to)
  }

  object WeightedDirectedGraph extends WeightedDirectedGraph[DefaultNode, DefaultEdge] {
    type _Node = DefaultNode
    type _Edge = DefaultEdge

    override def init: (Seq[_Node], Seq[_Edge]) = {
      val tNode1 = new DefaultNode
      val tNode2 = new DefaultNode
      val tNode3 = new DefaultNode
      val tEdge1 = DefaultEdge(tNode1, tNode2, 10)
      val tEdge2 = DefaultEdge(tNode2, tNode3, 12)

      (Seq(tNode1, tNode2, tNode3), Seq(tEdge1, tEdge2))
    }
  }

}