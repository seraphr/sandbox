package jp.seraphr.sandbox.graph.generics

trait DirectedGraph[_Node <: Node[_Node, _Edge], _Edge <: Edge[_Node, _Edge]]
  extends Graph[_Node, _Edge]

trait DirectedEdge[_Node <: Node[_Node, _Edge], _Edge <: DirectedEdge[_Node, _Edge]]
  extends Edge[_Node, _Edge] {

  val from: _Node
  val to: _Node
}