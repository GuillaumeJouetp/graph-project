package graphs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Node {

    private String lat;
    private String lng;
    private Map<String, String[]> lignes;
    private String nom;
    private String num;
    private String type;
    private String isHub;
    private ArrayList<Edge> neighbours = new ArrayList<>();

    public ArrayList<Edge> getNeighbours() {
        return this.neighbours;
    }

    public Edge getSpecificNeighbours(Node destination) {
        for(Edge edge:neighbours){
            if (edge.getDestinationNode() == destination){
                return edge;
            }
        }
        return null;
    }


    public void addNeighbour(Edge edge){
        this.neighbours.add(edge);
    }

    public String getNom(){
        return this.nom;
    }

    public String getType(){
        return this.type;
    }

    public String getLat(){
        return this.lat;
    }

    public String getLng() {
        return this.lng;
    }

    public String toString(){
        return getNom()+" "+getType()+"\n";
    }

    public String getNum() {
        return num;
    }
}
