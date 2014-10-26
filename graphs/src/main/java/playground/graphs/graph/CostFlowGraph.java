package playground.graphs.graph;

import java.util.HashMap;
import java.util.Map;

public class CostFlowGraph<V> implements WeightedGraph<V> {
  private final DirectedGraphImpl<V> graph;
  private final Map<V, Map<V, Integer>> capacities = new HashMap<V, Map<V, Integer>>();
  private final Map<V, Map<V, Integer>> costs = new HashMap<V, Map<V, Integer>>();
  private AsWeight weightOpt = AsWeight.USE_CAPACITY_AS_WEIGHT;

  public CostFlowGraph(DirectedGraphImpl<V> graph) {
    this.graph = graph;
  }

  public CostFlowGraph() {
    this(new DirectedGraphImpl<V>());
  }

  public void useCapacityAsWeight() {
    weightOpt = AsWeight.USE_CAPACITY_AS_WEIGHT;
  }

  public void useCostAsWeight() {
    weightOpt = AsWeight.USE_COST_AS_WEIGHT;
  }

  public CostFlowGraph<V> addEdgeWithCapacityCost(V source, V target, int capacity, int cost) {
    graph.addEdge(source, target);
    setCapacity(source, target, capacity);
    setCost(source, target, cost);
    return this;
  }

  private void setCost(V source, V target, int cost) {
    Map<V, Integer> map = costs.get(source);
    if (map == null) {
      map = new HashMap<V, Integer>();
      costs.put(source, map);
    }
    map.put(target, cost);
  }

  private void setCapacity(V source, V target, int capacity) {
    Map<V, Integer> map = capacities.get(source);
    if (map == null) {
      map = new HashMap<V, Integer>();
      capacities.put(source, map);
    }
    map.put(target, capacity);
  }

  public int getCapacity(V source, V target) {
    return capacities.get(source).get(target);
  }

  public int getCost(V source, V target) {
    return costs.get(source).get(target);
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

  @Override
  public int getWeight(V source, V target) {
    if (weightOpt.equals(AsWeight.USE_CAPACITY_AS_WEIGHT)) {
      return getCapacity(source, target);
    }
    return getCost(source, target);
  }

  @Override
  public void setWeight(V source, V target, int w) {
    if (weightOpt.equals(AsWeight.USE_CAPACITY_AS_WEIGHT)) {
      setCapacity(source, target, w);
    } else {
      setCost(source, target, w);
    }
  }

  private enum AsWeight {
    USE_CAPACITY_AS_WEIGHT,
    USE_COST_AS_WEIGHT
  }
}
