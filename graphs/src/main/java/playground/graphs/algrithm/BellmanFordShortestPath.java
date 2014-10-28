package playground.graphs.algrithm;

import playground.graphs.graph.WeightedDirectedGraph;

import java.util.*;

public class BellmanFordShortestPath<V> {

  private final WeightedDirectedGraph<V> graph;
  private final V source;
  private final Map<V, Integer> dist = new HashMap<>();
  private final Map<V, V> predecessor = new HashMap<>();

  public BellmanFordShortestPath(WeightedDirectedGraph<V> weightedGraph, V source) {
    this.graph = weightedGraph;
    this.source = source;
    buildShortestPath();
  }

  private void buildShortestPath() {
    // add initialize dist
    for (V v : graph.getVertices()) {
      dist.put(v, Integer.MAX_VALUE);
    }
    dist.put(source, 0);
    predecessor.put(source, null);

    boolean updated;
    // |V| iterations
    for (V v : graph.getVertices()) {
      updated = false;
      // check each edge through vertex's neighbours
      for (V from : graph.getVertices()) {
        if (!predecessor.containsKey(from)) {
          // path to from is not yet computed
          continue;
        }
        for (V to : graph.getOutgoingVertices(from)) {
          Integer currLength = dist.get(to);
          Integer newLength = dist.get(from) + graph.getWeight(from, to);
          if (newLength < currLength) {
            dist.put(to, newLength);
            predecessor.put(to, from);
            updated = true;
          }
        }
      }
      if (!updated) {
        break;
      }
    }
  }

  public void refresh() {
    dist.clear();
    predecessor.clear();
    buildShortestPath();
  }

  public Integer getPathTotalWeights(V target) {
    return dist.get(target);
  }

  public Iterable<V> getPath(V target) {
    Deque<V> path = new ArrayDeque<>();
    if (predecessor.get(target) == null) {
      // no path to target
      return path;
    } else {
      // add target
      path.add(target);
    }

    V curr = predecessor.get(target);
    while (curr != null) {
      path.addFirst(curr);
      curr = predecessor.get(curr);
    }
    return Collections.unmodifiableCollection(path);
  }
}
