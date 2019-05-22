
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
        Graph graph = objectMapper.readValue(new URL("http://vasyenmetro.com/data/reseau.json"), Graph.class);
        graph.createAgencyList();
        System.out.println("BFS diameter : "+graph.getBFSDiameter()+" stations");
        System.out.println("Djikstra diameter : "+graph.getDjikstraDiameter()+" km");

        /*
        System.out.println("--------------------------BFS------------------------------");
        BFS bfs = new BFS(graph.getNode("2015"));
        bfs.printPath(graph.getNode("2016"));

        System.out.println(" ");
        System.out.println("--------------------------Djikstra------------------------------");
        Djikstra djikstra = new Djikstra(graph,"2015");
        djikstra.printPath(graph.getNode("2016"));
        */
    }
}
