package jp.seraphr.sandbox.graph.generics

trait WeightedDirectedGraph[_Node <: Node[_Node, _Edge], _Edge <: WeightedDirectedEdge[_Node, _Edge]]
  extends WeightedGraph[_Node, _Edge]
  with DirectedGraph[_Node, _Edge]

trait WeightedDirectedEdge[_Node <: Node[_Node, _Edge], _Edge <: WeightedDirectedEdge[_Node, _Edge]]
  extends WeightedEdge[_Node, _Edge]
  with DirectedEdge[_Node, _Edge]