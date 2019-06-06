
import com.fasterxml.jackson.databind.ObjectMapper;
import graphs.Graph;
import graphs.Node;
import graphs.UndirectedEdge;
import search.BFS;
import search.Djikstra;

import java.io.*;
import java.net.URL;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        /*convert json string to object*/
        Graph graph = objectMapper.readValue(new URL("http://vasyenmetro.com/data/reseau.json"), Graph.class);
        graph.createAgencyList();

        Node node1 = graph.getNode("2015");
        Node node2 = graph.getNode("2016");

        System.out.println("FROM "+ node1.getNom()+" TO "+node2.getNom());
        System.out.println("-------------------------- BFS ------------------------------");
        BFS bfs = new BFS(node1);
        bfs.printPath(node2);

        System.out.println();

        System.out.println("-------------------------- Djikstra -------------------------");
        Djikstra djikstra = new Djikstra(graph,node1.getNum());
        djikstra.printPath(node2);


        graph.printBFSDiameter();
        graph.printDjikstraDiameter();

        System.out.println();
        List<UndirectedEdge> hbEdges = graph.getHighestBetweennessEdge();
        System.out.println("Highest betweenness edge : " + hbEdges.get(0));
        System.out.println("Second highest betweenness edge : " + hbEdges.get(1));
        System.out.println("Third highest betweenness edge : " + hbEdges.get(2));

        graph.separateClusters(5);





    }
}
