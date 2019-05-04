package graphs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown=true)

public class RawGraph {

    private Map<String, Node> stations;     //Map entre les noms des stations et les objets stations
    private Route[] routes;                 //liste des routes
    private Map<Node,ArrayList<Edge>> adgencyList;   //Map entre les Node et leurs edges

    public void createAgencyList(){
        this.adgencyList = new HashMap<>(); //crée l'adgency list

        for (Route route: this.routes){                         //on récupère les routes
            String[] listeArrets = route.getArrets();           //on récupère la liste des arrêts de la route

            for (int i = 0; i<listeArrets.length - 1 ;i++ ){

                String numeroStation = listeArrets[i];
                Node station = this.stations.get(numeroStation);

                String numerorProchaineStation = listeArrets[i+1];
                Node nextStation = this.stations.get(numerorProchaineStation);

                Edge newEdge = new Edge(station, nextStation,route.getType(),route.getLigne());

                if (this.adgencyList.containsKey(station)){
                    // si contient déjà la station, on ajoute juste le nouvel edge
                    ArrayList<Edge> existingListOfEdges = this.adgencyList.get(station);
                    boolean testExistence = false; //possible d'avoir déjà l'edge venant d'une autre route
                    for (Edge edge:existingListOfEdges){
                        if (edge.getDestinationNode() == newEdge.getDestinationNode()){
                            testExistence = true;
                            break;
                        }
                    }
                    if (!testExistence){
                        existingListOfEdges.add(newEdge);
                        this.adgencyList.put(station, existingListOfEdges);
                    }


                }else{
                    // sinon on créé la clé et on ajoute le nouvel edge
                    ArrayList<Edge> newListOfEdges = new ArrayList<>();
                    newListOfEdges.add(newEdge);
                    this.adgencyList.put(station, newListOfEdges);
                }

            }
        }
    }

    public void printEdges(Node station){
        System.out.println(this.adgencyList.get(station));
    }

    public Route[] getRoutes(){
        return this.routes;
    }

    public Node getNode(String stationNumber){
        return this.stations.get(stationNumber);
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

}
