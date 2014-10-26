package playground.graphs.graph;

import java.util.HashMap;
import java.util.Map;

public class WeightedGraphImpl<V> implements WeightedGraph<V> {
  private final Graph<V> graph;
  private final Map<V, Map<V, Integer>> outgoingWeight = new HashMap<V, Map<V, Integer>>();

  public WeightedGraphImpl(Graph<V> graph) {
    this.graph = graph;
  }

  @Override
  public int getWeight(V source, V target) {
    if (!graph.containsEdge(source, target)) {
      throw EdgeNotFoundException;
    }
    Map<V, Integer> outgoingWeights = outgoingWeight.get(source);
    if (outgoingWeights == null) {
      throw WeightNotFoundException;
    }
    return outgoingWeights.get(target);
  }

  public void setWeight(V source, V target, int w) {
    if (!graph.containsEdge(source, target)) {
      throw EdgeNotFoundException;
    }
    Map<V, Integer> outgoingWeights = outgoingWeight.get(source);
    if (outgoingWeights == null) {
      outgoingWeights = new HashMap<V, Integer>();
      outgoingWeight.put(source, outgoingWeights);
    }
    outgoingWeights.put(target, w);
  }

  public WeightedGraphImpl<V> addEdgeWithWeight(V source, V target, Integer w) {
    graph.addEdge(source, target);
    setWeight(source, target, w);
    return this;
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
