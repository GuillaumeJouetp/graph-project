package search;

import graphs.Edge;
import graphs.Node;

import java.util.*;

public class BFS {

    private Node originNode;
    private Map<Node, Node> previousNodes;
    private Map<Node, Boolean> visited;
    private Map<Node, Integer> weights;

    public BFS(Node originNode){
        this.originNode = originNode;
        this.previousNodes = new HashMap<>();
        this.visited = new HashMap<>();
        this.weights = new HashMap<>();

        this.doBFS();
    }

    private void doBFS(){

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(originNode);

        this.previousNodes.put(originNode,originNode);
        this.weights.put(originNode,0);

        while(queue.size() != 0){
            Node node = queue.pop();
            this.visited.put(node, true);

            for(Edge neighbours : node.getNeighbours()){
                Node neighbourNode = neighbours.getDestinationNode();
                if (!this.visited.containsKey(neighbourNode)){
                    queue.add(neighbourNode);
                    this.previousNodes.put(neighbourNode,node);
                    int weightOfNode = this.weights.get(node);
                    this.weights.put(neighbourNode,weightOfNode+1);
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
        System.out.println("Number of stations : " + weights.get(destination));

        System.out.println(getPath(destination));
    }
}
