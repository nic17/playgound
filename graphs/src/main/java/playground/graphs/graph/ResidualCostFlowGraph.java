package playground.graphs.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResidualCostFlowGraph<V> extends ResidualGraph<V> implements CostFlowGraph<V> {
  private final CostFlowGraph<V> graph;
  private AsWeight weightOpt = AsWeight.USE_COST_AS_WEIGHT;

  public ResidualCostFlowGraph(CostFlowGraph<V> graph) {
    this.graph = graph;
    initializeFlows();
  }

  public void useCapacityAsWeight() {
    weightOpt = AsWeight.USE_CAPACITY_AS_WEIGHT;
  }

  public void useCostAsWeight() {
    weightOpt = AsWeight.USE_COST_AS_WEIGHT;
  }

  @Override
  protected void initializeFlows() {
    for (V u : graph.getVertices()) {
      for (V v : graph.getOutgoingVertices(u)) {
        Map<V, Integer> subFlows = flows.get(u);
        if (subFlows == null) {
          subFlows = new HashMap<V, Integer>();
          flows.put(u, subFlows);
        }
        subFlows.put(v, 0);
        subFlows = flows.get(v);
        if (subFlows == null) {
          subFlows = new HashMap<V, Integer>();
          flows.put(v, subFlows);
        }
        subFlows.put(u, 0);
      }
    }
  }

  public int getCapacity(V source, V target) {
    if (graph.containsEdge(source, target)) {
      // forward edge
      return graph.getCapacity(source, target) - getFlow(source, target);
    } else if (graph.containsEdge(target, source)) {
      // backward edge
      return getFlow(target, source);
    }
    throw EdgeNotFoundException;
  }

  public int getCost(V source, V target) {
    if (graph.containsEdge(source, target)) {
      // forward edge
      return graph.getCost(source, target);
    } else if (graph.containsEdge(target, source)) {
      // backward edge
      return -graph.getCost(target, source);
    }
    throw EdgeNotFoundException;
  }

  @Override
  public int getWeight(V source, V target) {
    if (weightOpt.equals(AsWeight.USE_COST_AS_WEIGHT)) {
      return getCost(source, target);
    } else if (weightOpt.equals(AsWeight.USE_CAPACITY_AS_WEIGHT)) {
      return getCapacity(source, target);
    }
    throw EdgeNotFoundException;
  }

  @Override
  public boolean containsVertex(V vertex) {
    return graph.containsVertex(vertex);
  }

  @Override
  public boolean containsEdge(V source, V target) {
    if (graph.containsEdge(source, target)) {
      // forward edge
      return graph.getCapacity(source, target) > getFlow(source, target);
    } else if (graph.containsEdge(target, source)) {
      // backward edge
      return getFlow(target, source) > 0;
    } else {
      return false;
    }
  }

  @Override
  public Iterable<V> getVertices() {
    return graph.getVertices();
  }

  public int getFlow(V source, V target) {
    return flows.get(source).get(target);
  }

  public void setFlow(V source, V target, int w) {
    flows.get(source).put(target, w);
    flows.get(target).put(source, -w);
  }

  public void increaseFlow(V source, V target, int m) {
    int f = getFlow(source, target);
    setFlow(source, target, f + m);
  }

  @Override
  public Iterable<V> getOutgoingVertices(V vertex) {
    List<V> vList = new ArrayList<V>();
    // forward edges
    for (V target : graph.getOutgoingVertices(vertex)) {
      if (this.containsEdge(vertex, target)) {
        vList.add(target);
      }
    }
    //backward edge
    for (V source : graph.getIncomingVertices(vertex)) {
      if (this.containsEdge(vertex, source)) {
        vList.add(source);
      }
    }
    return vList;
  }

  @Override
  public Iterable<V> getIncomingVertices(V vertex) {
    List<V> vList = new ArrayList<V>();
    // forward edges
    for (V source : graph.getIncomingVertices(vertex)) {
      if (this.containsEdge(source, vertex)) {
        vList.add(source);
      }
    }
    // backward edges
    for (V target : graph.getOutgoingVertices(vertex)) {
      if (this.containsEdge(target, vertex)) {
        vList.add(target);
      }
    }
    return vList;
  }
}

