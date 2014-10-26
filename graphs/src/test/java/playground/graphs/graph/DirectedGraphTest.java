package playground.graphs.graph;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Iterator;

import static org.testng.Assert.*;

public class DirectedGraphTest {

  private final String vertices[] = new String[10];
  private final DirectedGraphImpl<String> graph = new DirectedGraphImpl<String>();

  @BeforeClass
  public void init() {
    for (int i = 0; i < 10; i++) {
      vertices[i] = "vertex" + i;
    }
  }

  @BeforeMethod
  public void setUp() throws Exception {
    for (String v : vertices) {
      graph.addVertex(v);
    }
  }

  @AfterMethod
  public void tearDown() throws Exception {
    graph.clearAll();
  }

  @Test
  public void testAddEdge() throws Exception {
    assertTrue(graph.addEdge(vertices[0], vertices[1]));
    assertFalse(graph.addEdge(vertices[0], vertices[1]));
  }

  @Test
  public void testContainsEdge() throws Exception {
    assertFalse(graph.containsEdge(vertices[0], vertices[1]));
    assertTrue(graph.addEdge(vertices[0], vertices[1]));
    assertTrue(graph.containsEdge(vertices[0], vertices[1]));
  }

  @Test
  public void testDeleteEdge() throws Exception {
    assertFalse(graph.deleteEdge(vertices[0], vertices[1]));
    assertTrue(graph.addEdge(vertices[0], vertices[1]));
    assertTrue(graph.deleteEdge(vertices[0], vertices[1]));
    assertFalse(graph.containsEdge(vertices[0], vertices[1]));
  }

  @Test
  public void testClearEdges() throws Exception {
    assertTrue(graph.addEdge(vertices[0], vertices[1]));
    assertTrue(graph.containsEdge(vertices[0], vertices[1]));
    graph.clearEdges();
    assertFalse(graph.containsEdge(vertices[0], vertices[1]));
  }

  @Test
  public void testGetNeighbours() throws Exception {
    assertEquals(graph.getNeighbours(vertices[0]), Collections.EMPTY_SET);
    for (int i = 1; i < 10; i++) {
      assertTrue(graph.addEdge(vertices[0], vertices[i]));
    }
    Iterator<String> iter = graph.getNeighbours(vertices[0]).iterator();
    for (int i = 1; i < 10; i++) {
      assertEquals(iter.next(), vertices[i]);
    }
    assertFalse(iter.hasNext());
  }

  @Test
  public void testAddVertex() throws Exception {
    assertTrue(graph.addVertex("new"));
    assertFalse(graph.addVertex("new"));
  }

  @Test
  public void testContainsVertex() throws Exception {
    assertTrue(graph.containsVertex(vertices[0]));
    assertFalse(graph.containsVertex("no"));
  }

  @Test
  public void testDeleteVertex() throws Exception {
    assertTrue(graph.deleteVertex(vertices[0]));
    assertFalse(graph.deleteVertex(vertices[0]));
  }

  @Test
  public void testClearVertex() throws Exception {
    graph.clearVertex();
    assertFalse(graph.getVertices().iterator().hasNext());
  }

  @Test
  public void testClearAll() throws Exception {
    graph.clearAll();
    assertFalse(graph.getVertices().iterator().hasNext());
  }

  @Test
  public void testGetVertices() throws Exception {
    Iterator<String> iter = graph.getVertices().iterator();
    for (int i = 0; i < 10; i++) {
      assertEquals(iter.next(), vertices[i]);
    }
    assertFalse(iter.hasNext());
  }
}