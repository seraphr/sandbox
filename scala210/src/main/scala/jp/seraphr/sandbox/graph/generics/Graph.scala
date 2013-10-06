package jp.seraphr.sandbox.graph.generics


/**
 * Graph trait内に Node / Edgeを定義すると、_Node / _Edgeを参照できないので、フラットにするしか無い。
 */
trait Graph[_Node <: Node[_Node, _Edge], _Edge <: Edge[_Node, _Edge]] {
  private val mNodeEdge: (Seq[_Node], Seq[_Edge]) = init
  def init: (Seq[_Node], Seq[_Edge])

  val (nodes, edges) = mNodeEdge
}

trait Node[_Node <: Node[_Node, _Edge], _Edge <: Edge[_Node, _Edge]] {
  val graph: Graph[_Node, _Edge]

  def edges: Seq[_Edge] = graph.edges.filter(e => e.node1 == this || e.node2 == this)
}

trait Edge[_Node <: Node[_Node, _Edge], _Edge <: Edge[_Node, _Edge]] {
  val nodes: (_Node, _Node)
  val node1 = nodes._1
  val node2 = nodes._2
}