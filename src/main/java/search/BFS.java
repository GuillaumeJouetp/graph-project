package search;

import graphs.Edge;
import graphs.Node;

import java.util.*;

public class BFS {

    private Node originNode;
    private Map<Node, Node> previousNodes;
    private Set<Node> visited;
    private Map<Node, Double> weights;

    public BFS(Node originNode){
        this.originNode = originNode;
        this.previousNodes = new HashMap<>();
        this.visited = new HashSet<>();
        this.weights = new HashMap<>();

        this.doBFS();
    }

    private void doBFS(){

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(originNode);

        this.previousNodes.put(originNode,originNode);
        this.weights.put(originNode,0.0);

        while(queue.size() != 0){
            Node node = queue.pop();
            this.visited.add(node);

            for(Edge neighbours : node.getNeighbours()){
                Node adjacentNode = neighbours.getDestinationNode();
                if (!this.visited.contains(adjacentNode)){
                    queue.add(adjacentNode);
                    double newWeight = neighbours.getWeight();
                    this.previousNodes.put(adjacentNode,node);
                    double weightOfNode = this.weights.get(node);
                    this.weights.put(adjacentNode,weightOfNode + newWeight);
                }
            }
        }
    }

    private ArrayList<Node> getPath(Node destination){
        ArrayList<Node> path = new ArrayList<>();
        path.add(destination);
        while (previousNodes.get(destination) != this.originNode){
            Node previousNode = previousNodes.get(destination);
            path.add(previousNode);
            destination = previousNode;
        }
        path.add(this.originNode);
        Collections.reverse(path);
        return (path);
    }

    public void printPath(Node destination){
        System.out.println("From : " + originNode.getNom());
        System.out.println("To : " + destination.getNom());
        System.out.println("Weight : " + weights.get(destination));

        System.out.println(getPath(destination));
    }
}
