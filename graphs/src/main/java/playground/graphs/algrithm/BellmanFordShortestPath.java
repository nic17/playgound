package playground.graphs.algrithm;

import playground.graphs.graph.WeightedGraph;

public class BellmanFordShortestPath<V, W extends Comparable> {

  private final WeightedGraph<V, W> weightedGraph;
  private final V source;

  public BellmanFordShortestPath(WeightedGraph<V, W> weightedGraph, V source) {
    this.weightedGraph = weightedGraph;
    this.source = source;
  }

  public W getPathTotalWeights(V target) {
    return weightedGraph.getWeight(source, target);
  }

  public Iterable<V> getPath(V target) {
    return null;
  }
}
