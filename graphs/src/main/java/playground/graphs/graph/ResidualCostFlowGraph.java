package playground.graphs.graph;

public class ResidualCostFlowGraph<V> implements WeightedGraph<V> {
  private final CostFlowGraph<V> graph;

  public ResidualCostFlowGraph(CostFlowGraph<V> graph) {
    this.graph = graph;
  }

  @Override
  public int getWeight(V source, V target) {
    return 0;
  }

  @Override
  public void setWeight(V source, V target, int w) {

  }

  @Override
  public boolean addVertex(V vertex) {
    throw CannotModifyThroughResidualGraph;
  }

  @Override
  public boolean containsVertex(V vertex) {
    return false;
  }

  @Override
  public boolean deleteVertex(V vertex) {
    throw CannotModifyThroughResidualGraph;
  }

  @Override
  public boolean addEdge(V source, V target) {
    throw CannotModifyThroughResidualGraph;
  }

  @Override
  public boolean containsEdge(V source, V target) {
    return false;
  }

  @Override
  public boolean deleteEdge(V source, V target) {
    throw CannotModifyThroughResidualGraph;
  }

  @Override
  public void clearEdges() {
    throw CannotModifyThroughResidualGraph;
  }

  @Override
  public void clearVertex() {
    throw CannotModifyThroughResidualGraph;
  }

  @Override
  public void clearAll() {
    throw CannotModifyThroughResidualGraph;
  }

  @Override
  public Iterable<V> getNeighbours(V vertex) {
    return null;
  }

  @Override
  public Iterable<V> getVertices() {
    return null;
  }
}
