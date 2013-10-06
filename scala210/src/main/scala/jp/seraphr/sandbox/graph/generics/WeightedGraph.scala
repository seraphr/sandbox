package jp.seraphr.sandbox.graph.generics

trait WeightedGraph[_Node <: Node[_Node, _Edge], _Edge <: WeightedEdge[_Node, _Edge]]
  extends Graph[_Node, _Edge] {

}

trait WeightedEdge[_Node <: Node[_Node, _Edge], _Edge <: WeightedEdge[_Node, _Edge]]
  extends Edge[_Node, _Edge] {

  val weight: Int
}