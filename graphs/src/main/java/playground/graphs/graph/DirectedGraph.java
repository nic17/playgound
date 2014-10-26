package playground.graphs.graph;

public interface DirectedGraph<V> extends Graph<V> {
  Iterable<V> getOutgoingVertices(V vertex);

  Iterable<V> getIncomingVertices(V vertex);
}
