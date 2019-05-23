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

                Edge newEdge = new Edge(originStation, destinationStation,route.getType(),route.getLigne());

                ArrayList<Edge> originStationEdges = originStation.getNeighbours();

                boolean testExistence = false;

                for(Edge edge: originStationEdges){
                    if (edge.getDestinationNode() == newEdge.getDestinationNode()){
                        testExistence = true;
                        break;
                    }
                }

                if (!testExistence){
                    originStation.addNeighbour(newEdge);
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

    /*
    public double getDjikstraDiameter(){
        double diameter = 0.;
        for (Node node : this.getListStations()){
            Djikstra djikstra = new Djikstra(this,node.getNum());
            diameter = max(diameter,djikstra.getLongestPath());
        }
        return diameter;
    }
    */
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

}
