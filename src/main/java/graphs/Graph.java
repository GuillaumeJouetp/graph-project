package graphs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import search.BFS;
import search.Djikstra;
import search.MyTuple;

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

    public MyTuple<BFS,Node> getBFSDiameter(){
        int maxDiameter = 0;
        BFS bfsDiameter = null;
        Node destDiameter = null;
        for (Node node : this.getListStations()){
            BFS bfs = new BFS(node);
            Node destinationNode = bfs.getLongestPathDestination();
            int diameter = bfs.getCount(destinationNode);
            if (maxDiameter < diameter){
                maxDiameter = diameter;
                bfsDiameter = bfs;
                destDiameter = destinationNode;
            }
        }
        MyTuple<BFS,Node> tuple = new MyTuple<>(bfsDiameter,destDiameter);

        return tuple;
    }

    public void printBFSDiameter(){
        MyTuple<BFS,Node> tuple = getBFSDiameter();
        Node destinationNode = tuple.getDestinationNode();
        BFS bfs = tuple.getSearchMethod();
        System.out.println();
        System.out.println("################### BFS diameter ###################");
        System.out.print("From : " + bfs.getOriginNode());
        System.out.print("To : " + destinationNode);
        System.out.println("Diameter : "+ bfs.getCount(destinationNode));
        System.out.println("Path : ");
        System.out.println(bfs.getPath(destinationNode));

    }

    public MyTuple<Djikstra,Node> getDjikstraDiameter(){
        double maxDiameter = 0;
        Djikstra djikstraDiameter = null;
        Node destDiameter = null;
        for (Node node : this.getListStations()){
            Djikstra djikstra = new Djikstra(this,node.getNum());
            Node destinationNode = djikstra.getLongestPathDestination();
            double diameter = djikstra.getNodeWeight(destinationNode);
            if (maxDiameter < diameter){
                maxDiameter = diameter;
                djikstraDiameter = djikstra;
                destDiameter = destinationNode;
            }
        }
        MyTuple<Djikstra,Node> tuple = new MyTuple<>(djikstraDiameter,destDiameter);
        return tuple;
    }

    public void printDjikstraDiameter(){
        MyTuple<Djikstra,Node> tuple = getDjikstraDiameter();
        Node destinationNode = tuple.getDestinationNode();
        Djikstra djikstra = tuple.getSearchMethod();
        System.out.println();
        System.out.println("################### Djikstra diameter ###################");
        System.out.print("From : " + djikstra.getOriginNode());
        System.out.print("To : " + destinationNode);
        System.out.println("Diameter : "+ djikstra.getNodeWeight(destinationNode));
        System.out.println("Path : ");
        System.out.println(djikstra.getPath(destinationNode));

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

    public void separateClusters(){
        List<UndirectedEdge> betweenness = getHighestBetweennessEdge();
        Node testNode = getListStations().get(0);
        Node finalNode = null;
        boolean isPath = true;
        while(isPath){
            BFS bfs = new BFS(testNode);
            UndirectedEdge edgeToRemove = betweenness.remove(0);
            Node nodeToRemoveEdge1 = edgeToRemove.getNodes()[0];
            Node nodeToRemoveEdge2 = edgeToRemove.getNodes()[1];

            nodeToRemoveEdge1.removeEdge(nodeToRemoveEdge2);
            nodeToRemoveEdge2.removeEdge(nodeToRemoveEdge1);

            for(Node node:getListStations()){
                if (!bfs.isPath(node)){
                    isPath = false;
                    System.out.println("Other cluster contains : "+node);
                    finalNode = node;
                    break;
                }
            }
        }
        BFS bfs1 = new BFS(testNode);
        BFS bfs2 = new BFS(finalNode);
        System.out.println("##################### FIRST CLUSTER ######################");
        bfs1.printNodeList();
        System.out.println();
        System.out.println("##################### SECOND CLUSTER ######################");
        bfs2.printNodeList();
        System.out.println();
        System.out.println("Can you get to "+getNode("1716").getNom()+ " from "+getNode("B_1821").getNom()+" ? ");
        BFS bfs = new BFS(getNode("1716"));
        System.out.println(bfs.isPath(getNode("B_1821")));
    }
}
