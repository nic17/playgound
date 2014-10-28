package playground.graphs.graph;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;

public class ResidualCostFlowGraphTest {

  private ResidualCostFlowGraph<String> graph;
  private String v1 = "v1", v2 = "v2", v3 = "v3";

  @BeforeMethod
  public void setUp() throws Exception {
    CostFlowGraphImpl<String> cfGraph = new CostFlowGraphImpl<String>();
    cfGraph.addVertex(v1);
    cfGraph.addVertex(v2);
    cfGraph.addVertex(v3);
    cfGraph.addEdgeWithCapacityCost(v1, v2, 10, 2);
    cfGraph.addEdgeWithCapacityCost(v3, v1, 10, 3);
    graph = new ResidualCostFlowGraph(cfGraph);
  }

  @Test
  public void testGetWeight() throws Exception {
    Assert.assertEquals(graph.getWeight(v1, v2), 2);
    Assert.assertEquals(graph.getWeight(v2, v1), -2);
  }

  @Test
  public void testSetFlow() throws Exception {
    Assert.assertFalse(graph.containsEdge(v2, v1));
    graph.setFlow(v1, v2, 3);
    Assert.assertTrue(graph.containsEdge(v1, v2));
    Assert.assertTrue(graph.containsEdge(v2, v1));
    graph.setFlow(v1, v2, 10);
    Assert.assertFalse(graph.containsEdge(v1, v2));
    Assert.assertTrue(graph.containsEdge(v2, v1));
  }

  @Test
  public void testGetOutgoingVertices() throws Exception {
    for (String v : graph.getOutgoingVertices(v1)) {
      Assert.assertEquals(v, v2);
    }
    for (String v : graph.getIncomingVertices(v1)) {
      Assert.assertEquals(v, v3);
    }
    graph.setFlow(v1, v2, 2);
    graph.setFlow(v3, v1, 3);
    Iterator<String> iter = graph.getOutgoingVertices(v1).iterator();
    Assert.assertEquals(iter.next(), v2);
    Assert.assertEquals(iter.next(), v3);
    iter = graph.getIncomingVertices(v1).iterator();
    Assert.assertEquals(iter.next(), v3);
    Assert.assertEquals(iter.next(), v2);
    Assert.assertEquals(graph.getWeight(v1, v3), -3);
  }

  @Test
  public void testAsWeight() throws Exception {
    graph.setFlow(v1, v2, 2);
    graph.setFlow(v3, v1, 3);
    graph.useCapacityAsWeight();

    Iterator<String> iter = graph.getOutgoingVertices(v1).iterator();
    Assert.assertEquals(iter.next(), v2);
    Assert.assertEquals(iter.next(), v3);
    iter = graph.getIncomingVertices(v1).iterator();
    Assert.assertEquals(iter.next(), v3);
    Assert.assertEquals(iter.next(), v2);
    Assert.assertEquals(graph.getWeight(v1, v3), 3);
    Assert.assertEquals(graph.getWeight(v1, v2), 8);
  }

}