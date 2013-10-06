package jp.seraphr.sandbox.graph.typeMember

trait Graph {
  graph =>

  type _Node <: Node
  type _Edge <: Edge

  private val mNodeEdge: (Seq[_Node], Seq[_Edge]) = init
  def init: (Seq[_Node], Seq[_Edge])

  val (nodes, edges) = mNodeEdge

  trait Node {
    def edges: Seq[_Edge] = graph.edges.filter(e => e.node1 == this || e.node2 == this)
  }

  trait Edge {
    val nodes: (_Node, _Node)
    val node1 = nodes._1
    val node2 = nodes._2
  }
}