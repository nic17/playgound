package playground.graphs.graph;

import java.util.HashMap;
import java.util.Map;

public abstract class ResidualGraph<V> implements WeightedDirectedGraph<V> {
  protected final Map<V, Map<V, Integer>> flows = new HashMap<>();

  protected abstract void initializeFlows();

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
  public final void setWeight(V source, V target, int w) {
    throw CannotModifyThroughResidualGraph;
  }

  @Override
  public final boolean addVertex(V vertex) {
    throw CannotModifyThroughResidualGraph;
  }

  @Override
  public final boolean deleteVertex(V vertex) {
    throw CannotModifyThroughResidualGraph;
  }

  @Override
  public final boolean addEdge(V source, V target) {
    throw CannotModifyThroughResidualGraph;
  }

  @Override
  public final boolean deleteEdge(V source, V target) {
    throw CannotModifyThroughResidualGraph;
  }

  @Override
  public final void clearEdges() {
    throw CannotModifyThroughResidualGraph;
  }

  @Override
  public final void clearVertex() {
    throw CannotModifyThroughResidualGraph;
  }

  @Override
  public final void clearAll() {
    throw CannotModifyThroughResidualGraph;
  }

  @Override
  public final Iterable<V> getNeighbours(V vertex) {
    throw UnsupportedThroughResidualGraph;
  }
}
