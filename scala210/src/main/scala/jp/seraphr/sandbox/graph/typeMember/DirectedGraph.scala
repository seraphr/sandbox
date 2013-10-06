package jp.seraphr.sandbox.graph.typeMember

trait DirectedGraph extends Graph {
  type _Edge <: DirectedEdge


  trait DirectedEdge extends super.Edge {
    val from: _Node
    val to: _Node
  }
}