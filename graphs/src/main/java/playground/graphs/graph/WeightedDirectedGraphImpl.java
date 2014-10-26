package playground.graphs.graph;

public class WeightedDirectedGraphImpl<V> extends WeightedGraphImpl<V> implements WeightedDirectedGraph<V> {
  private final DirectedGraph<V> graph;

  public WeightedDirectedGraphImpl(DirectedGraph<V> graph) {
    super(graph);
    this.graph = graph;
  }

  @Override
  public Iterable<V> getOutgoingVertices(V vertex) {
    return graph.getOutgoingVertices(vertex);
  }

  @Override
  public Iterable<V> getIncomingVertices(V vertex) {
    return graph.getIncomingVertices(vertex);
  }
}
