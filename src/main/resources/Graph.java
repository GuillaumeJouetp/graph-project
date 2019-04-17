

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


/**
 * This class is an Abstract Graph.
 * Types of Vertices (V) and Edges (E) are not defined by default.
 * The only constraints on theses types are that Vertices must be Comparable ie.
 * there must be a way to order vertices with one another. This is done through the use of the Comparable interface.
 *
 *
 * @author slefebvr
 *
 * @param <V>
 * @param <E>
 */
public abstract class Graph<V extends Comparable<V>> {

    protected int n;
    protected int m;


    public Graph(int nbVertices, int nbEdges) {
        this.n= nbVertices;
        this.m = nbEdges;
    }


    /**
     * Initializes an empty graph
     */
    public Graph() {
    }

    public int order() {
    }

    public int size() {
    }

    /**
     * Adds the corresponding vertex if needed
     * @param e
     */
    public abstract void addEdge(V s, V t);

    /**
     * Adds a vertex to the graph. On success n is increased
     * @param v
     */
    public abstract void addVertex(V v);

    /**
     *
     * @return the list of the graph vertices
     */
    public abstract List<V> vertices();

    /**
     * Return the list of v's neighbors
     * @param v
     */
    public abstract List<V> inNeighbors(V v);
    public abstract List<V> outNeighbors(V v);

    /**
     * Returns the inbound degree of node v
     * in case of undirected graph should return the same as outDegree
     * @param v
     * @return inbound degree of vertex v
     */
    public abstract int inDegree(V v);
    public abstract int outDegree(V v);



    /**
     * Returns true if the graph is connected
     * can be more efficient.
     * @return
     */
    public boolean isConnected() {
    }

    private static<V extends Comparable<V>> void assign(V u, V root, Graph<V> g, Map<V, V> components) {

    }

    /**
     * Counts and returns the number of connected components
     * Kosaraju's algorithm
     * @param g
     * @return
     */
    public static<V extends Comparable<V>> int cc(Graph<V> g) {



    }



    private static <V extends Comparable<V>> V findMin(List<V> l) {

    }

    /**
     * Returns the list of vertices ordered by DFS visit
     *
     * @param g
     * @return
     */
    public static<V extends Comparable<V>>  List<V> dfs(Graph<V> g) {

    }

		return out;
}
    private static<V extends Comparable<V>>  void dfs(Graph<V> g, V start, List<V> out) {

    }


    /**
     * Iterative function for bfs
     * @param g
     * @return
     */
    public static<V extends Comparable<V>> List<V> bfs(Graph<V> g, V start) {
        List<V> vertices = g.vertices();
        List<V> out = new LinkedList<>();
..........	ToDo
        return out;

    }


    private static<V extends Comparable<V>> void bfs(Graph<V> g, V v, List<V> vertices, List<V> out) {
        Queue<V> q = new LinkedList<>();
        q.add(v);
        do {
............ToDo

        } while(q.size() > 0);
    }

}