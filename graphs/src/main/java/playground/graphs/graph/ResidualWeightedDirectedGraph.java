package playground.graphs.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResidualWeightedDirectedGraph<V> extends ResidualGraph<V> {
  private final WeightedDirectedGraph<V> graph;

  public ResidualWeightedDirectedGraph(WeightedDirectedGraph<V> graph) {
    this.graph = graph;
    initializeFlows();
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

  @Override
  public int getWeight(V source, V target) {

    if (graph.containsEdge(source, target)) {
      // forward edge
      return graph.getWeight(source, target) - getFlow(source, target);
    } else if (graph.containsEdge(target, source)) {
      // backward edge
      return getFlow(target, source);
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
      return graph.getWeight(source, target) > getFlow(source, target);
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
}
