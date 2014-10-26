package playground.graphs.algrithm;

import playground.graphs.graph.WeightedDirectedGraph;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class EdmondsKarpMaxFlow<V> {
  private final WeightedDirectedGraph<V> graph;
  private final V source, sink;
  private final Map<V, Map<V, Integer>> flows = new HashMap<V, Map<V, Integer>>();
  private final Map<V, V> predecessor = new HashMap<V, V>();
  private final Map<V, Integer> maxFlow = new HashMap<V, Integer>();
  private int currMaxFlow = 0;


  public EdmondsKarpMaxFlow(WeightedDirectedGraph<V> graph, V source, V sink) {
    this.graph = graph;
    this.source = source;
    this.sink = sink;
    init();
    computeMaxFlow();
  }

  private void computeMaxFlow() {
    Queue<V> que = new ArrayDeque<V>();
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
      setFlow(pre, curr, getFlow(pre, curr) + m);
      curr = pre;
      pre = predecessor.get(curr);
    }
  }

  private void breathFirstSearch(Queue<V> que) {
    predecessor.clear();
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
        int cap = graph.getWeight(u, v);
        int flow = getFlow(u, v);
        if (flow < cap) {
          que.add(v);
          predecessor.put(v, u);
          maxFlow.put(v, Math.min(maxFlow.get(u), cap - flow));
        }
      }
    }

  }

  private void init() {
    resetMaxFlow();
    for (V u : graph.getVertices()) {
      for (V v : graph.getNeighbours(u)) {
        setFlow(u, v, 0);
      }
    }
  }

  private void resetMaxFlow() {
    for (V u : graph.getVertices()) {
      maxFlow.put(u, 0);
    }
    maxFlow.put(source, Integer.MAX_VALUE);
  }

  private void setFlow(V u, V v, int i) {
    Map<V, Integer> subFlows = flows.get(u);
    if (subFlows == null) {
      subFlows = new HashMap<V, Integer>();
      flows.put(u, subFlows);
    }
    subFlows.put(v, i);
  }

  public int getMaxFlow() {
    return currMaxFlow;
  }

  public int getFlow(V from, V to) {
    return flows.get(from).get(to);
  }
}
