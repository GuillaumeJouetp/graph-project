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
    private ArrayList<DirectedEdge> neighbours = new ArrayList<>();

    public ArrayList<DirectedEdge> getNeighbours() {
        return this.neighbours;
    }


    public void removeEdge(Node node){
        int i = 0;
        while(i<neighbours.size()){
            DirectedEdge edge = neighbours.get(i);
            if (edge.destinationNode == node){
                neighbours.remove(edge);
            }
            else {
                i++;
            }
        }
    }

    public void addNeighbour(DirectedEdge directedEdge) {
        this.neighbours.add(directedEdge);
    }
    public DirectedEdge getSpecificNeighbours(Node destination) {
        for(DirectedEdge edge:neighbours){
            if (edge.getDestinationNode() == destination){
                return edge;
            }
        }
        return null;
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
        return getNom()+" "+getType();
    }

    public String getNum() {
        return num;
    }
}
