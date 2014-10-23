package playground.graphs.graph;

import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<V, W extends Comparable> implements Graph<V> {
  private static final UnsupportedOperationException EdgeNotFoundException = new UnsupportedOperationException("Edge cannot be found");
  private final Graph<V> graph;
  private final Map<V, Map<V, W>> outgoingWeight = new HashMap<V, Map<V, W>>();

  public WeightedGraph(Graph<V> graph) {
    this.graph = graph;
  }

  public W getWeight(V source, V target) {
    if (!graph.containsEdge(source, target)) {
      throw EdgeNotFoundException;
    }
    Map<V, W> outgoingWeights = outgoingWeight.get(source);
    if (outgoingWeights == null) {
      return null;
    }
    return outgoingWeights.get(target);
  }

  public void setWeight(V source, V target, W w) {
    if (!graph.containsEdge(source, target)) {
      throw EdgeNotFoundException;
    }
    Map<V, W> outgoingWeights = outgoingWeight.get(source);
    if (outgoingWeights == null) {
      outgoingWeights = new HashMap<V, W>();
      outgoingWeight.put(source, outgoingWeights);
    }
    outgoingWeights.put(target, w);

  }

  @Override
  public boolean addVertex(V vertex) {
    return graph.addVertex(vertex);
  }

  @Override
  public boolean containsVertex(V vertex) {
    return graph.containsVertex(vertex);
  }

  @Override
  public boolean deleteVertex(V vertex) {
    return graph.deleteVertex(vertex);
  }

  @Override
  public boolean addEdge(V source, V target) {
    return graph.addEdge(source, target);
  }

  @Override
  public boolean containsEdge(V source, V target) {
    return graph.containsEdge(source, target);
  }

  @Override
  public boolean deleteEdge(V source, V target) {
    return graph.deleteEdge(source, target);
  }

  @Override
  public void clearEdges() {
    graph.clearEdges();
  }

  @Override
  public void clearVertex() {
    graph.clearVertex();
  }

  @Override
  public void clearAll() {
    graph.clearAll();
  }

  @Override
  public Iterable<V> getNeighbours(V vertex) {
    return graph.getNeighbours(vertex);
  }

  @Override
  public Iterable<V> getVertices() {
    return graph.getVertices();
  }
}
