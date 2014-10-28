package playground.graphs.graph;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CostFlowGraphTest {

  @Test
  public void testAddEdgeWithCapacityCost() throws Exception {
    CostFlowGraphImpl<String> graph = new CostFlowGraphImpl<String>();
    String v1 = "v1", v2 = "v2";
    graph.addVertex(v1);
    graph.addVertex(v2);
    graph.addEdgeWithCapacityCost(v1, v2, 1, 2);
    Assert.assertEquals(graph.getCapacity(v1, v2), 1);
    Assert.assertEquals(graph.getCost(v1, v2), 2);
  }
}