package playground.graphs.graph;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WeightedGraphTest {

  private final String v[] = new String[10];
  private final DirectedGraphImpl<String> graph = new DirectedGraphImpl<>();

  @BeforeClass
  public void init() {
    for (int i = 0; i < 10; i++) {
      v[i] = "vertex" + i;
      graph.addVertex(v[i]);
    }
  }

  @Test
  public void testGetWeight() throws Exception {
    WeightedGraphImpl<String> weightedGraph = new WeightedGraphImpl<>(graph);
    for (int i = 1; i < 10; i++) {
      weightedGraph.addEdge(v[0], v[i]);
      weightedGraph.setWeight(v[0], v[i], i);
      Assert.assertEquals(weightedGraph.getWeight(v[0], v[i]), i);
    }
  }
}