
import com.fasterxml.jackson.databind.ObjectMapper;
import graphs.Graph;
import search.BFS;
import search.Djikstra;

import java.io.*;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        /*convert json string to object*/
        Graph g = objectMapper.readValue(new URL("http://vasyenmetro.com/data/reseau.json"), Graph.class);
        g.createAgencyList();
        g.printEdges(g.getNode("1839"));

        System.out.println("--------------------------BFS------------------------------");
        BFS bfs = new BFS(g.getNode("1913"));
        bfs.printPath(g.getNode("1839"));
        //System.out.println("Graph Object\n"+ rawGraph);

        System.out.println(" ");
        System.out.println("--------------------------Djikstra------------------------------");
        Djikstra djikstra = new Djikstra(g,"1913");
        djikstra.printPath(g.getNode("1839"));
    }
}
