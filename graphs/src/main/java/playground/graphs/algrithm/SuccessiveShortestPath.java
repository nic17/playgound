package playground.graphs.algrithm;

import playground.graphs.graph.CostFlowGraph;
import playground.graphs.graph.ResidualCostFlowGraph;

import java.util.Iterator;

public class SuccessiveShortestPath<V> {
  private final ResidualCostFlowGraph<V> graph;
  private int totalCost = 0;

  public SuccessiveShortestPath(CostFlowGraph<V> costFlowGraph, V source, V sink) {
    this.graph = new ResidualCostFlowGraph<>(costFlowGraph);
    BellmanFordShortestPath<V> bfShortestPath = new BellmanFordShortestPath<>(graph, source);
    Iterable<V> path = bfShortestPath.getPath(sink);
    while (path.iterator().hasNext()) {
      int m = findMinCapacity(path.iterator());
      if (m == 0) break;
      augmentFlow(path.iterator(), m);
      bfShortestPath.refresh();
      path = bfShortestPath.getPath(sink);
    }
  }

  private void augmentFlow(Iterator<V> iter, int m) {
    V source = iter.next();
    V target;
    while (iter.hasNext()) {
      target = iter.next();
      graph.increaseFlow(source, target, m);
      totalCost += graph.getCost(source, target) * m;
      source = target;
    }

  }

  private int findMinCapacity(Iterator<V> iter) {
    int m = Integer.MAX_VALUE;
    V source = iter.next();
    V target;
    while (iter.hasNext()) {
      target = iter.next();
      m = Math.min(m, graph.getCapacity(source, target));
      source = target;
    }
    return m;
  }

  public int getFlow(V source, V target) {
    return graph.getFlow(source, target);
  }

  public int getTotalCost() {
    return totalCost;
  }
}
