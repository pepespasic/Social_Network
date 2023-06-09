package GraphPackage;

import ADTPackage.LinkedStack;
import ADTPackage.QueueInterface;
import ADTPackage.StackInterface;

public class MainDU {
    private static DirectedGraph<String> myGraph = new DirectedGraph<>();
    private static StackInterface<String> path = new LinkedStack<>();
    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final String D = "D";
    private static final String E = "E";
    private static final String F = "F";
    private static final String G = "G";
    private static final String H = "H";
    private static final String I = "I";

    private static final String CS1  = "CS1";
    private static final String CS2  = "CS2";
    private static final String CS3  = "CS3";
    private static final String CS4  = "CS4";
    private static final String CS5  = "CS5";
    private static final String CS6  = "CS6";
    private static final String CS7  = "CS7";
    private static final String CS8  = "CS8";
    private static final String CS9  = "CS9";
    private static final String CS10 = "CS10";

    public static void main(String[] args)
    {
        System.out.println("Testing the directed, unweighted graph in Figure 29-10.");
        setGraphFig29_10();
        myGraph.displayEdges();
        checkVertexAndEdgeCount(9, 13);
        testEdgesFig29_10();
        System.out.println("-------------------------------------------------------");

        testBFS(A);
        System.out.println("A B D E G F H C I  Expected");
        System.out.println("-------------------------------------------------------");

        testDFS(A);
        System.out.println("A B E F C H I D G  Expected");
        System.out.println("-------------------------------------------------------");

        System.out.println("\nFinding the shortest path in the graph in Figure 29-15a:\n");
        testShortestPath(); // A -> E -> H
        System.out.println("-------------------------------------------------------");

        System.out.println("\nTesting topological sort of directed, unweighted graph in Figure 29-8:");
        setGraphFig29_08();
        myGraph.displayEdges();
        checkVertexAndEdgeCount(10, 11);
        testEdgesFig29_08();
        testTopSort();      // CS1   CS2   CS3   CS4  CS5  CS6   CS7  CS8   CS9   CS10

        System.out.println("Done.");
    }  // end main

    public static void setGraphFig29_10()
    {
        setVerticesFig29_10(); // Graph cleared before setting vertices
        setEdgesFig29_10();
    } // end setGraphFig29_10

    public static void setGraphFig29_08()
    {
        setVerticesFig29_08();
        setEdgesFig29_08();
    } // end setGraphFig29_08

    public static void checkVertexAndEdgeCount(int numberOfVertices, int numberOfEdges)
    {
        System.out.println("\nNumber of vertices = " + myGraph.getNumberOfVertices() +
                " (should be " + numberOfVertices + ")");
        System.out.println("Number of edges = " + myGraph.getNumberOfEdges() +
                " (should be " + numberOfEdges + ")");
    } // end checkVertexAndEdgeCount

    public static void testEdgesFig29_10()
    {
        // Check existing edges
        boolean ok = true;
        ok = checkEdge(A, B, ok);
        ok = checkEdge(A, D, ok);
        ok = checkEdge(A, E, ok);
        ok = checkEdge(B, E, ok);
        ok = checkEdge(C, B, ok);
        ok = checkEdge(D, G, ok);
        ok = checkEdge(E, F, ok);
        ok = checkEdge(E, H, ok);
        ok = checkEdge(F, C, ok);
        ok = checkEdge(F, H, ok);
        ok = checkEdge(G, H, ok);
        ok = checkEdge(H, I, ok);

        // Check some non-existing edges
        ok = checkNoEdge(A, I, ok);
        ok = checkNoEdge(C, E, ok);
        ok = checkNoEdge(C, F, ok);

        if (ok)
            System.out.println("Edges are OK.");
    } // end testEdgesFig29_10

    public static void testEdgesFig29_08()
    {
        // Check existing edges
        boolean ok = true;
        ok = checkEdge(CS1, CS2, ok);
        ok = checkEdge(CS2, CS3, ok);
        ok = checkEdge(CS2, CS4, ok);
        ok = checkEdge(CS2, CS5, ok);
        ok = checkEdge(CS4, CS6, ok);
        ok = checkEdge(CS4, CS7, ok);
        ok = checkEdge(CS5, CS10, ok);
        ok = checkEdge(CS6, CS8, ok);
        ok = checkEdge(CS7, CS8, ok);
        ok = checkEdge(CS7, CS9, ok);
        ok = checkEdge(CS9, CS10, ok);
        ok = checkEdge(H, I, ok);

        // Check some non-existing edges
        ok = checkNoEdge(CS1, CS3, ok);
        ok = checkNoEdge(CS1, CS5, ok);
        ok = checkNoEdge(CS4, CS10, ok);

        if (ok)
            System.out.println("Edges are OK.");
        myGraph.displayEdges();
    } // end testEdgesFig29_08

    private static boolean checkEdge(String vertex1, String vertex2, boolean ok)
    {
        boolean check = ok;
        if (!myGraph.hasEdge(vertex1, vertex2))
        {
            System.out.println("hasEdge error " + vertex1 + " " + vertex2);
            check = false;
        } // end if

        return check;
    } // end checkEdge

    private static boolean checkNoEdge(String vertex1, String vertex2, boolean ok)
    {
        boolean check = ok;
        if (myGraph.hasEdge(vertex1, vertex2))
        {
            System.out.println("hasEdge error " + vertex1 + " " + vertex2);
            check = false;
        } // end if

        return check;
    } // end checkNoEdge

    public static void testBFS(String v)
    {
        System.out.println("\n\nBreadth-First Traversal beginning at vertex " + v + ": ");
        QueueInterface<String> bfs = myGraph.getBreadthFirstTraversal(v);

        displayQueue(bfs);
        System.out.println(" Actual");
    } // end testBFS

    public static void testDFS(String v)
    {
        System.out.println("\n\nDepth-First Traversal beginning at vertex " + v + ": ");
        QueueInterface<String> dfs = myGraph.getDepthFirstTraversal(v);

        displayQueue(dfs);
        System.out.println(" Actual");
    } // end testDFS

    public static void testShortestPath()
    {
        // UNWEIGHTED graph in Figure 29-15a (same as Fig. 29-10)
        showPath(A, B, "A B");
        showPath(A, C, "A E F C");
        showPath(A, D, "A D");
        showPath(A, E, "A E");
        showPath(A, F, "A E F");
        showPath(A, G, "A D G");
        showPath(A, H, "A E H");
        showPath(A, I, "A E H I");
    } // end testShortestPath

    private static void showPath(String vertex1, String vertex2, String expectedPath)
    {
        System.out.print("\nThe shortest path from " + vertex1 + " to " + vertex2 + " is ");
        int length = myGraph.getShortestPath(vertex1, vertex2, path);
        displayStack(path);
        System.out.println(" and has a length of " + length + ".");
        System.out.println("Expected path:                   " + expectedPath);
    } // end showPath

    public static void testTopSort()
    {
        StackInterface<String> sort = myGraph.getTopologicalOrder();
        System.out.println("\n\nTopological order:");
        displayStack(sort);
        System.out.println(" Actual");
        System.out.println("CS1 CS2 CS3 CS4 CS5 CS6 CS7 CS8 CS9 CS10  Expected");
    } // end testTopSort

    public static void setVerticesFig29_08()
    {
        myGraph.clear();

        myGraph.addVertex(CS1);
        myGraph.addVertex(CS2);
        myGraph.addVertex(CS3);
        myGraph.addVertex(CS4);
        myGraph.addVertex(CS5);
        myGraph.addVertex(CS6);
        myGraph.addVertex(CS7);
        myGraph.addVertex(CS8);
        myGraph.addVertex(CS9);
        myGraph.addVertex(CS10);
    } // end setVerticesFig29_08

    public static void setEdgesFig29_08()
    {
        myGraph.addEdge(CS1, CS2);

        myGraph.addEdge(CS2, CS3);
        myGraph.addEdge(CS2, CS4);
        myGraph.addEdge(CS2, CS5);

        myGraph.addEdge(CS4, CS6);
        myGraph.addEdge(CS4, CS7);

        myGraph.addEdge(CS5, CS10);

        myGraph.addEdge(CS6, CS8);

        myGraph.addEdge(CS7, CS8);
        myGraph.addEdge(CS7, CS9);

        myGraph.addEdge(CS9, CS10);
    } // end setEdgesFig29_08

    public static void setVerticesFig29_10()
    {
        myGraph.clear();

        myGraph.addVertex(A);
        myGraph.addVertex(B);
        myGraph.addVertex(C);
        myGraph.addVertex(D);
        myGraph.addVertex(E);
        myGraph.addVertex(F);
        myGraph.addVertex(G);
        myGraph.addVertex(H);
        myGraph.addVertex(I);
    } // end setVerticesFig29_10

    public static void setEdgesFig29_10()
    {
        myGraph.addEdge(A, B);
        myGraph.addEdge(A, D);
        myGraph.addEdge(A, E);

        myGraph.addEdge(B, E);

        myGraph.addEdge(C, B);

        myGraph.addEdge(D, G);

        myGraph.addEdge(E, F);
        myGraph.addEdge(E, H);

        myGraph.addEdge(F, C);
        myGraph.addEdge(F, H);

        myGraph.addEdge(G, H);

        myGraph.addEdge(H, I);

        myGraph.addEdge(I, F);

/* Since additions are made to the end of each edge list,
   these lists appear as follows:
		A: B -> D -> E
		B: E
		C: B
		D: G
		E: F -> H
		F: C -> H
		G: H
		H: I
		I: F
   We can predict the BFS and DFS traversals knowing this. */
    } // end setEdgesFig29_10

    public static void displayStack(StackInterface<String> s)
    {
        while (!s.isEmpty())
            System.out.print(s.pop() + " ");

        assert(s.isEmpty());
    } // end displayStack

    public static void displayQueue(QueueInterface<String> q)
    {
        while (!q.isEmpty())
            System.out.print(q.dequeue() + " ");
    } // end displayQueue
}
