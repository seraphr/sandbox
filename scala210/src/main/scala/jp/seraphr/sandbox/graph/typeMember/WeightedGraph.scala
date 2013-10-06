package jp.seraphr.sandbox.graph.typeMember

trait WeightedGraph extends Graph {
  type _Edge <: WeightedEdge


  trait WeightedEdge extends super.Edge {
    val weight: Int
  }
}