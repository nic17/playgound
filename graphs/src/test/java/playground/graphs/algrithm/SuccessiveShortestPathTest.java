package playground.graphs.algrithm;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import playground.graphs.graph.CostFlowGraphImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SuccessiveShortestPathTest {
  private final String v[] = new String[7];
  private SuccessiveShortestPath<String> ssp;

  @BeforeClass
  public void init() {
    CostFlowGraphImpl<String> cfGraph = new CostFlowGraphImpl<>();
    List<Integer> eightInt = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      eightInt.add(i);
    }
    Random random = new Random();
    Collections.shuffle(eightInt, random);
    for (Integer i : eightInt) {
      v[i] = "vertex" + i;
      cfGraph.addVertex(v[i]);
    }

    cfGraph.addEdgeWithCapacityCost(v[0], v[1], 5, 0);
    cfGraph.addEdgeWithCapacityCost(v[1], v[2], 7, 1).addEdgeWithCapacityCost(v[1], v[3], 7, 5);
    cfGraph.addEdgeWithCapacityCost(v[2], v[3], 2, -2).addEdgeWithCapacityCost(v[2], v[4], 3, 8);
    cfGraph.addEdgeWithCapacityCost(v[3], v[4], 3, -3).addEdgeWithCapacityCost(v[3], v[5], 2, 4);
    cfGraph.addEdgeWithCapacityCost(v[4], v[6], 3, 0);
    cfGraph.addEdgeWithCapacityCost(v[5], v[6], 2, 0);
    ssp = new SuccessiveShortestPath<>(cfGraph, v[0], v[6]);
  }

  @Test
  public void testGetFlow() throws Exception {
    Assert.assertEquals(ssp.getFlow(v[0], v[1]), 5);

    Assert.assertEquals(ssp.getFlow(v[1], v[2]), 2);
    Assert.assertEquals(ssp.getFlow(v[1], v[3]), 3);

    Assert.assertEquals(ssp.getFlow(v[2], v[3]), 2);
    Assert.assertEquals(ssp.getFlow(v[2], v[4]), 0);

    Assert.assertEquals(ssp.getFlow(v[3], v[4]), 3);
    Assert.assertEquals(ssp.getFlow(v[3], v[5]), 2);

    Assert.assertEquals(ssp.getFlow(v[4], v[6]), 3);

    Assert.assertEquals(ssp.getFlow(v[5], v[6]), 2);
  }

  @Test
  public void testGetTotalCost() throws Exception {
    Assert.assertEquals(ssp.getTotalCost(), 12);
  }

  @Test(timeOut = 400)
  public void testPerformance() throws Exception {
    CostFlowGraphImpl<String> cfGraph = new CostFlowGraphImpl<>();
    String source = "source", sink = "sink";
    cfGraph.addVertex(source);
    cfGraph.addVertex(sink);
    List<String> machines = new ArrayList<>(), layouts = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      machines.add("machine" + i);
    }
    Random random = new Random();
    Collections.shuffle(machines, random);
    for (String machine : machines) {
      cfGraph.addVertex(machine);
      // each machine has capacity in [1, 10]
      cfGraph.addEdgeWithCapacityCost(source, machine, random.nextInt(10) + 1, 0);
    }

    for (int i = 0; i < 10; i++) {
      layouts.add("layout" + i);
    }
    Collections.shuffle(layouts, random);
    for (String layout : layouts) {
      cfGraph.addVertex(layout);
      // each layout has capacity in [1, 100]
      cfGraph.addEdgeWithCapacityCost(layout, sink, random.nextInt(100) + 1, 0);
    }


    for (String machine : machines) {
      for (String layout : layouts) {
        // machine-layout edge has cost in [10, 20]
        cfGraph.addEdgeWithCapacityCost(machine, layout, 10, random.nextInt(11) + 10);
      }
    }
    Assert.assertTrue(new SuccessiveShortestPath<>(cfGraph, source, sink).getTotalCost() > 100);
  }
}