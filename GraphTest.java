// --== CS400 File Header Information ==--
// Name: Jake Germano
// Email: jgermano@wisc.edu
// Team: AF
// TA: Ilay
// Lecturer: Gary Dahl
// Notes to Grader: none


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.junit.platform.console.ConsoleLauncher;
import java.lang.invoke.MethodHandles;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

    private CS400Graph<String> graph;

    /**
     * Instantiate graph from last week's shortest path activity.
     */
    @BeforeEach
    public void createGraph() {
        graph = new CS400Graph<>();
        // insert vertices A-F
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        // insert edges from Week 11. Shortest Path Activity
        graph.insertEdge("A","B",6);
        graph.insertEdge("A","C",2);
        graph.insertEdge("A","D",5);
        graph.insertEdge("B","E",1);
        graph.insertEdge("B","C",2);
        graph.insertEdge("C","B",3);
        graph.insertEdge("C","F",1);
        graph.insertEdge("D","E",3);
        graph.insertEdge("E","A",4);
        graph.insertEdge("F","A",1);
        graph.insertEdge("F","D",1);
    }

    /**
     * Test from 9)
     * Tests that it does get the shortest distance from D -> B is 12
     */
    @Test
    public void testPathCostDtoB() {
        assertTrue(graph.getPathCost("D", "B") == 12);
    }

    /**
     * Test from 10)
     * Tests that it does get the shortest distance from D -> B is D E A C B
     */
    @Test
    public void testPathToDtoB() {
        assertEquals(graph.shortestPath("D", "B").toString(), "[D, E, A, C, B]");
    }

    /**
     * Test from 11)
     * Tests that it does get the shortest distance from E -> F is 7
     */
    @Test
    public void testPathCostEtoF() {
        assertTrue(graph.getPathCost("E", "F") == 7);
    }

    /**
     * Test from 12)
     * Tests what is the predecessor to the node B along the shortest path from F to B?
     */
    @Test
    public void testPredecessorFtoB() {
        assertEquals(graph.shortestPath("F", "B").get(graph.shortestPath("F", "B").size() -2), "C");
    }


    /**
     * Test from 13)
     * Makes sure that the exceptions are properly thrown
     */
    @Test
    public void testNoSuchExc(){
        //Exception exception =
        assertThrows(NoSuchElementException.class, () -> {
            graph.shortestPath("F", "Q");
        });

//        String expectedMessage = "The keys do not exist!";
//        String actualMessage = exception.getMessage();
//        assertTrue(actualMessage.contains(expectedMessage));
    }


    /**
     * Checks the distance/total weight cost from the vertex A to F.
     */
    @Test
    public void testPathCostAtoF() {
        System.out.println("AtoF" + graph.getPathCost("A", "F"));
        System.out.println("AtoF" + graph.shortestPath("A", "F"));
        assertTrue(graph.getPathCost("A", "F") == 3);
    }

    @Test
    public void testPathCostAtoA() {
        assertTrue(graph.getPathCost("A", "A") == 0);
    }

    @Test
    public void testPathCostAtoC() {
        assertTrue(graph.getPathCost("A", "C") == 2);
    }
    /**
     * Checks the distance/total weight cost from the vertex A to D.
     */
    @Test
    public void testPathCostAtoD() {
//        System.out.println(graph.getPathCost("A", "D"));
//        System.out.println(graph.shortestPath("A", "D"));
        assertTrue(graph.getPathCost("A", "D") == 4);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * A to D.
     */
    @Test
    public void testPathAtoD() {
        assertTrue(graph.shortestPath("A", "D").toString().equals(
                "[A, C, F, D]"
        ));
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * A to F.
     */
    @Test
    public void testPathAtoF() {
        assertTrue(graph.shortestPath("A", "F").toString().equals(
                "[A, C, F]"
        ));
    }

    public static void main(String[] args) {
        String className = MethodHandles.lookup().lookupClass().getName();
        String classPath = System.getProperty("java.class.path").replace(" ", "\\ ");
        String[] arguments = new String[] {
                "-cp",
                classPath,
                "--include-classname=.*",
                "--select-class=" + className };
        ConsoleLauncher.main(arguments);
    }

}