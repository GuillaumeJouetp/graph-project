package graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public abstract class Graph {

    int order; // graph order (number of nodes)
    int size; // grap size (number of edges)

    abstract void addEdge(Edge edge);

    public abstract List<Node> neighbors(Node node);

    public abstract void showGraph();

    public int getOrder() {
        return this.order;
    }

    public int getSize() {
        return this.size;
    }

}
