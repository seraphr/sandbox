package jp.seraphr.sandbox.graph.typeMember

object Main {
  object WeightedDirectedGraph extends WeightedDirectedGraph {
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

    class DefaultNode extends super.Node
    case class DefaultEdge(from: _Node, to: _Node, weight: Int) extends super.WeightedDirectedEdge {
      override val nodes = (from, to)
    }
  }
}