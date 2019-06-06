package graphs;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

public class DirectedEdge extends Edge {

    /*distance entre les 2 lat/long des 2 nodes*/
    private Double weight;

    /*Données supplémentaires*/
    private String type;
    private String ligne;

    public DirectedEdge(Node sourceNode, Node destinationNode, String type, String ligne){
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.weight = this.getDistance(sourceNode,destinationNode);

        this.type = type;
        this.ligne = ligne;
    }

    public double getDistance(Node origin, Node destination){
        double x1 = Double.parseDouble(origin.getLat());
        double x2 = Double.parseDouble(destination.getLat());
        double y1 = Double.parseDouble(origin.getLng());
        double y2 = Double.parseDouble(destination.getLng());

        double euclidianDistance = sqrt(pow((x1-x2),2)+pow((y1-y2),2));

        return euclidianDistance*111.16;
    }

    public Node getSourceNode() {
        return sourceNode;
    }

    public Node getDestinationNode() {
        return destinationNode;
    }

    public String getType() {
        return type;
    }

    public String getLigne() {
        return ligne;
    }

    public double getWeight() {
        return weight;
    }

    public String toString(){
        return "From: "+ getSourceNode().getNom()+" to: "+getDestinationNode().getNom()+" via: "+getType()+" "+getLigne()+" weight : "+getWeight() +"\n";
    }
}
