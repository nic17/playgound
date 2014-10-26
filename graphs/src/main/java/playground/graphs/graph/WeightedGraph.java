package playground.graphs.graph;

public interface WeightedGraph<V> extends Graph<V> {
  int getWeight(V source, V target);

  void setWeight(V source, V target, int w);
}
