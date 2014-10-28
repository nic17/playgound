package playground.graphs.graph;

public interface CostFlowGraph<V> extends WeightedDirectedGraph<V> {
  void useCapacityAsWeight();

  void useCostAsWeight();

  int getCapacity(V source, V target);

  int getCost(V source, V target);

  public enum AsWeight {
    USE_CAPACITY_AS_WEIGHT,
    USE_COST_AS_WEIGHT
  }
}
