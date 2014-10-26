package playground.graphs.algrithm;

import playground.graphs.graph.WeightedDirectedGraph;

import java.util.*;

public class BellmanFordShortestPath<V> {

  private final WeightedDirectedGraph<V> graph;
  private final V source;
  private final Map<V, Integer> lengthMap = new HashMap<V, Integer>();
  private final Map<V, V> previousVertex = new HashMap<V, V>();

  public BellmanFordShortestPath(WeightedDirectedGraph<V> weightedGraph, V source) {
    this.graph = weightedGraph;
    this.source = source;
    buildShortestPath();
  }

  private void buildShortestPath() {
    // add initialize lengthMap
    for (V v : graph.getVertices()) {
      lengthMap.put(v, Integer.MAX_VALUE);
    }
    lengthMap.put(source, 0);
    previousVertex.put(source, null);

    boolean updated;
    // |V| iterations
    for (V v : graph.getVertices()) {
      updated = false;
      // check each edge through vertex's neighbours
      for (V from : graph.getVertices()) {
        if (!previousVertex.containsKey(from)) {
          // path to from is not yet computed
          continue;
        }
        for (V to : graph.getOutgoingVertices(from)) {
          Integer currLength = lengthMap.get(to);
          Integer newLength = lengthMap.get(from) + graph.getWeight(from, to);
          if (newLength < currLength) {
            lengthMap.put(to, newLength);
            previousVertex.put(to, from);
            updated = true;
          }
        }
      }
      if (!updated) {
        break;
      }
    }
  }

  public Integer getPathTotalWeights(V target) {
    return lengthMap.get(target);
  }

  public Iterable<V> getPath(V target) {
    Deque<V> path = new ArrayDeque<V>();
    if (previousVertex.get(target) == null) {
      // no path to target
      return path;
    } else {
      // add target
      path.add(target);
    }

    V curr = previousVertex.get(target);
    while (curr != null) {
      path.addFirst(curr);
      curr = previousVertex.get(curr);
    }
    return Collections.unmodifiableCollection(path);
  }
}
