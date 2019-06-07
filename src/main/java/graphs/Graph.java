package graphs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import search.BFS;
import search.Dijkstra;
import utils.Tuple;

import java.util.*;

import static java.lang.Math.max;


@JsonIgnoreProperties(ignoreUnknown=true)
public class Graph {

    private Map<String, Node> stations;     //Map entre les ID des stations et les objets stations
    private Route[] routes;                 //liste des routes

    public void createAgencyList(){
        for (Route route : this.routes){                         //on récupère les routes

            String[] listeArrets = route.getArrets();           //on récupère la liste des arrêts de la route

            for (int i = 0; i<listeArrets.length - 1 ;i++ ){

                String numeroStation = listeArrets[i];
                Node originStation = this.stations.get(numeroStation);

                String numerorProchaineStation = listeArrets[i+1];
                Node destinationStation = this.stations.get(numerorProchaineStation);

                DirectedEdge newDirectedEdge = new DirectedEdge(originStation, destinationStation,route.getType(),route.getLigne());

                ArrayList<DirectedEdge> originStationDirectedEdges = originStation.getNeighbours();

                boolean testExistence = false;

                for(DirectedEdge directedEdge : originStationDirectedEdges){
                    if (directedEdge.getDestinationNode() == newDirectedEdge.getDestinationNode()){
                        testExistence = true;
                        break;
                    }
                }

                if (!testExistence){
                    originStation.addNeighbour(newDirectedEdge);
                }
            }
        }

        this.stations.remove("2022");
        this.stations.remove("2021");

    }

    public void printEdges(Node station){
        System.out.println(station.getNeighbours());
    }

    public Route[] getRoutes(){
        return this.routes;
    }

    public Node getNode(String stationNumber){
        return this.stations.get(stationNumber);
    }

    public Tuple<BFS,Node> getBFSDiameter(){
        int maxDiameter = 0;
        BFS bfsDiameter = null;
        Node destDiameter = null;
        for (Node node : this.getListStations()){
            BFS bfs = new BFS(node);
            Node destinationNode = bfs.getLongestPathDestination();
            if(bfs.isPath(destinationNode)) {
                int diameter = bfs.getCount(destinationNode);

                if (maxDiameter < diameter) {
                    maxDiameter = diameter;
                    bfsDiameter = bfs;
                    destDiameter = destinationNode;
                }
            }
        }
        Tuple<BFS,Node> tuple = new Tuple<>(bfsDiameter,destDiameter);

        return tuple;
    }

    public void printBFSDiameter(){
        Tuple<BFS,Node> tuple = getBFSDiameter();
        Node destinationNode = tuple.getDestinationNode();
        BFS bfs = tuple.getSearchMethod();
        System.out.println();
        System.out.println("-------------------------- BFS diameter --------------------------");
        System.out.println("From : " + bfs.getOriginNode());
        System.out.println("To : " + destinationNode);
        System.out.println("Diameter : "+ bfs.getCount(destinationNode)+" stations");
        System.out.println("Path -------------------------------------");
        bfs.printPath(destinationNode);

    }


    public Tuple<Dijkstra,Node> getDijkstraDiameter(){
        double maxDiameter = 0;
        Dijkstra dijkstraDiameter = null;
        Node destDiameter = null;
        for (Node node : this.getListStations()){
            Dijkstra dijkstra = new Dijkstra(this,node.getNum());
            Node destinationNode = dijkstra.getLongestPathDestination();
            if(dijkstra.isPath(destinationNode)) {
                double diameter = dijkstra.getNodeWeight(destinationNode);
                if (maxDiameter < diameter) {
                    maxDiameter = diameter;
                    dijkstraDiameter = dijkstra;
                    destDiameter = destinationNode;
                }
            }
        }
        Tuple<Dijkstra,Node> tuple = new Tuple<>(dijkstraDiameter,destDiameter);
        return tuple;
    }

    public void printDijkstraDiameter(){
        Tuple<Dijkstra,Node> tuple = getDijkstraDiameter();
        Node destinationNode = tuple.getDestinationNode();
        Dijkstra dijkstra = tuple.getSearchMethod();
        System.out.println();
        System.out.println("-------------------------- Dijkstra diameter --------------------------");
        System.out.println("From : " + dijkstra.getOriginNode());
        System.out.println("To : " + destinationNode);
        System.out.println("Diameter : "+ dijkstra.getNodeWeight(destinationNode)+" km");
        System.out.println("Path -------------------------------------");
        dijkstra.printPath(destinationNode);
    }

    public List<UndirectedEdge> getHighestBetweennessEdge(){
        List<UndirectedEdge> edges = new ArrayList<>();
        for (Node n1 : this.getListStations()){
            BFS bfs = new BFS(n1);
            for (Node n2 : this.getListStations()) {
                if (n1 != n2 && bfs.isPath(n2)) {
                    UndirectedEdge.addEdgesFromPath(edges, bfs.getPath(n2));
                }
            }
        }
        edges.sort(Comparator.comparing(UndirectedEdge::getEncounters));
        Collections.reverse(edges);
        return edges;
    }

    public Map<String, Node> getStations(){
        return this.stations;
    }

    public ArrayList<Node> getListStations(){
        Set keys = this.stations.keySet();
        ArrayList<Node> listStation = new ArrayList<>();
        for (Object key: keys){
            listStation.add(this.stations.get(key));
        }
        return listStation;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("***** Raw Graph Details *****\n");
        sb.append("Routes = "+Arrays.toString(getRoutes())+"\n");
        sb.append("Stations = "+getListStations()+"\n");

        return sb.toString();
    }

    public void separateClusters(int clusterNumber ){
        List<UndirectedEdge> betweenness = getHighestBetweennessEdge();
        Node testNode = getListStations().get(0);
        Node finalNode = null;
        List<Node> cluster = new ArrayList<>();
        cluster.add(testNode);
        while(cluster.size() != clusterNumber ){

            BFS bfs = new BFS(testNode);

            UndirectedEdge edgeToRemove = betweenness.remove(0);

            Node nodeToRemoveEdge1 = edgeToRemove.getNodes()[0];
            Node nodeToRemoveEdge2 = edgeToRemove.getNodes()[1];

            nodeToRemoveEdge1.removeEdge(nodeToRemoveEdge2);
            nodeToRemoveEdge2.removeEdge(nodeToRemoveEdge1);

            for(Node node:getListStations()){
                boolean alreadyInOtherCluster = false;

                for(int i =0; i<cluster.size();i++){
                    if (cluster.get(i) != testNode) {
                        BFS bfsTest = new BFS(cluster.get(i));
                        Set<Node> set = bfsTest.getVisited();
                        if (set.contains(node)){
                            alreadyInOtherCluster = true;
                            break;
                        }
                    }
                }

                if (!bfs.isPath(node) && !alreadyInOtherCluster){
                    cluster.add(node);
                    BFS bfs1 = new BFS(node);
                    if (bfs1.getSize()> bfs.getSize()){
                        testNode = node;
                    }
                    break;
                }
            }
        }
        int i = 1;
        for(Node node : cluster) {

            BFS bfs = new BFS(node);
            System.out.println("##################### CLUSTER "+i+" ######################");
            bfs.printNodeList();
            System.out.println();
            i++;
        }
    }
}
