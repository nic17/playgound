package playground.graphs.algrithm;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import playground.graphs.graph.DirectedGraphImpl;
import playground.graphs.graph.WeightedDirectedGraphImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EdmondsKarpMaxFlowTest {

  private final String v[] = new String[8];
  private final WeightedDirectedGraphImpl<String> graph = new WeightedDirectedGraphImpl<>(new DirectedGraphImpl<String>());

  @BeforeClass
  public void init() {

    List<Integer> eightInt = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      eightInt.add(i);
    }
    Random random = new Random();
    Collections.shuffle(eightInt, random);
    for (Integer i : eightInt) {
      v[i] = "vertex" + i;
      graph.addVertex(v[i]);
    }

    graph.addEdgeWithWeight(v[0], v[1], 5).addEdgeWithWeight(v[0], v[2], 2);
    graph.addEdgeWithWeight(v[1], v[2], 3).addEdgeWithWeight(v[1], v[3], 3);
    graph.addEdgeWithWeight(v[2], v[3], 7);
    graph.addEdgeWithWeight(v[3], v[4], 5).addEdgeWithWeight(v[3], v[5], 7).addEdgeWithWeight(v[3], v[6], 1);
    graph.addEdgeWithWeight(v[4], v[5], 3).addEdgeWithWeight(v[4], v[7], 2);
    graph.addEdgeWithWeight(v[5], v[7], 4);
    graph.addEdgeWithWeight(v[6], v[7], 1);
  }

  @Test
  public void testGetMaxFlow() throws Exception {
    Assert.assertEquals(new EdmondsKarpMaxFlow<>(graph, v[0], v[7]).getMaxFlow(), 7);
  }

  @Test
  public void testGetFlow() throws Exception {
    EdmondsKarpMaxFlow<String> maxFlow = new EdmondsKarpMaxFlow<>(graph, v[0], v[7]);

    Assert.assertEquals(maxFlow.getFlow(v[0], v[1]), 5);
    Assert.assertEquals(maxFlow.getFlow(v[0], v[2]), 2);

    Assert.assertEquals(maxFlow.getFlow(v[1], v[2]), 2);
    Assert.assertEquals(maxFlow.getFlow(v[1], v[3]), 3);

    Assert.assertEquals(maxFlow.getFlow(v[2], v[3]), 4);

    Assert.assertEquals(maxFlow.getFlow(v[3], v[4]), 2);
    Assert.assertEquals(maxFlow.getFlow(v[3], v[5]), 4);
    Assert.assertEquals(maxFlow.getFlow(v[3], v[6]), 1);

    Assert.assertEquals(maxFlow.getFlow(v[4], v[5]), 0);
    Assert.assertEquals(maxFlow.getFlow(v[4], v[7]), 2);

    Assert.assertEquals(maxFlow.getFlow(v[5], v[7]), 4);

    Assert.assertEquals(maxFlow.getFlow(v[6], v[7]), 1);
  }

  @Test
  public void testBackwardEdge() throws Exception {
    String s = "source", v1 = "v1", v2 = "v2", v3 = "v3", v4 = "v4", v5 = "v5", t = "sink";
    WeightedDirectedGraphImpl<String> myGraph = new WeightedDirectedGraphImpl<>(new DirectedGraphImpl<String>());
    myGraph.addVertex(s);
    myGraph.addVertex(v1);
    myGraph.addVertex(v2);
    myGraph.addVertex(v3);
    myGraph.addVertex(v4);
    myGraph.addVertex(v5);
    myGraph.addVertex(t);
    myGraph.addEdgeWithWeight(s, v1, 10).addEdgeWithWeight(s, v2, 1).addEdgeWithWeight(s, v3, 1);
    myGraph.addEdgeWithWeight(v1, v3, 6).addEdgeWithWeight(v1, v4, 5);
    myGraph.addEdgeWithWeight(v2, v4, 1);
    myGraph.addEdgeWithWeight(v3, v5, 7);
    myGraph.addEdgeWithWeight(v4, t, 5);
    myGraph.addEdgeWithWeight(v5, t, 7);

    EdmondsKarpMaxFlow<String> maxFlow = new EdmondsKarpMaxFlow<>(myGraph, s, t);
    Assert.assertEquals(maxFlow.getMaxFlow(), 12);
    Assert.assertEquals(maxFlow.getFlow(s, v1), 10);
    Assert.assertEquals(maxFlow.getFlow(s, v2), 1);
    Assert.assertEquals(maxFlow.getFlow(s, v3), 1);
  }
}