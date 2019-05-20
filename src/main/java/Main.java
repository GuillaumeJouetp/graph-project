
import com.fasterxml.jackson.databind.ObjectMapper;
import search.BFS;
import graphs.RawGraph;
import search.Djikstra;

import java.io.*;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        /*convert json string to object*/
        RawGraph rawGraph = objectMapper.readValue(new URL("http://vasyenmetro.com/data/reseau.json"), RawGraph.class);
        rawGraph.createAgencyList();
        rawGraph.printEdges(rawGraph.getNode("1839"));

        System.out.println("--------------------------BFS------------------------------");
        BFS bfs = new BFS(rawGraph.getNode("1913"));
        bfs.printPath(rawGraph.getNode("1839"));
        //System.out.println("RawGraph Object\n"+ rawGraph);

        System.out.println(" ");
        System.out.println("--------------------------Djikstra------------------------------");
        Djikstra djikstra = new Djikstra(rawGraph.getNode("1913"));
        djikstra.printPath(rawGraph.getNode("1839"));
    }
}
