package jp.seraphr.sandbox.graph.typeMember

trait WeightedDirectedGraph extends WeightedGraph with DirectedGraph {
  type _Edge <: WeightedDirectedEdge

  trait WeightedDirectedEdge extends super.WeightedEdge with super.DirectedEdge
}