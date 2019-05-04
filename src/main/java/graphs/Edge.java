package graphs;

public class Edge {

    private Node originNode;
    private Node destinationNode;
    private String type;
    private String ligne;

    public Edge(Node originNode, Node destinationNode, String type, String ligne){
        this.originNode = originNode;
        this.destinationNode = destinationNode;
        this.type = type;
        this.ligne = ligne;
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

    public String toString(){
        return "From: "+getOriginNode().getNom()+" to: "+getDestinationNode().getNom()+" via: "+getType()+" "+getLigne()+"\n";
    }
}
