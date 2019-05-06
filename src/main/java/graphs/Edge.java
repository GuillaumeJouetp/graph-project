package graphs;


import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

public class Edge {

    private Node originNode;
    private Node destinationNode;
    private String type;
    private double size;
    private String ligne;

    public Edge(Node originNode, Node destinationNode, String type, String ligne){
        this.originNode = originNode;
        this.destinationNode = destinationNode;
        this.type = type;
        this.ligne = ligne;

        double x1 = Double.parseDouble(originNode.getLat());
        double x2 = Double.parseDouble(destinationNode.getLat());
        double y1 = Double.parseDouble(originNode.getLng());
        double y2 = Double.parseDouble(destinationNode.getLng());

        this.size = sqrt(pow((x1-x2),2)+pow((y1-y2),2)); //distance entre les 2 lat/long des 2 nodes

    }

    public Node getOriginNode() {
        return originNode;
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

    public double getSize() {
        return size;
    }

    public String toString(){
        return "From: "+getOriginNode().getNom()+" to: "+getDestinationNode().getNom()+" via: "+getType()+" "+getLigne()+" distance : "+getSize() +"\n";
    }
}
