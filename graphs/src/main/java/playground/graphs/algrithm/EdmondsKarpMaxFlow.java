package playground.graphs.algrithm;

import playground.graphs.graph.ResidualWeightedDirectedGraph;
import playground.graphs.graph.WeightedDirectedGraph;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class EdmondsKarpMaxFlow<V> {
  private final ResidualWeightedDirectedGraph<V> graph;
  private final V source, sink;
  private final Map<V, V> predecessor = new HashMap<>();
  private final Map<V, Integer> maxFlow = new HashMap<>();
  private int currMaxFlow = 0;


  public EdmondsKarpMaxFlow(WeightedDirectedGraph<V> graph, V source, V sink) {
    this.graph = new ResidualWeightedDirectedGraph<>(graph);
    this.source = source;
    this.sink = sink;
    computeMaxFlow();
  }

  private void computeMaxFlow() {
    Queue<V> que = new ArrayDeque<>();
    while (true) {
      breathFirstSearch(que);
      if (maxFlow.get(sink) == 0) {
        // no new path
        break;
      }
      augmentPath();
    }
  }

  private void augmentPath() {
    int m = maxFlow.get(sink);
    currMaxFlow += m;
    V curr = sink;
    V pre = predecessor.get(curr);
    while (pre != null) {
      graph.increaseFlow(pre, curr, m);
      curr = pre;
      pre = predecessor.get(curr);
    }
  }

  private void breathFirstSearch(Queue<V> que) {
    predecessor.clear();
    predecessor.put(source, null);
    que.clear();
    que.add(source);
    resetMaxFlow();
    while (!que.isEmpty()) {
      V u = que.remove();
      if (u.equals(sink)) {
        break;
      }
      for (V v : graph.getOutgoingVertices(u)) {
        if (predecessor.containsKey(v)) {
          // seen this vertex before
          continue;
        }
        int residual = graph.getWeight(u, v);
        que.add(v);
        predecessor.put(v, u);
        maxFlow.put(v, Math.min(maxFlow.get(u), residual));
      }
    }

  }

  private void resetMaxFlow() {
    for (V u : graph.getVertices()) {
      maxFlow.put(u, 0);
    }
    maxFlow.put(source, Integer.MAX_VALUE);
  }

  public int getMaxFlow() {
    return currMaxFlow;
  }

  public int getFlow(V from, V to) {
    return graph.getFlow(from, to);
  }
}
