package search;

import graphs.Edge;
import graphs.Node;

import java.util.*;

public class BFS extends Search {

    private Map<Node, Integer> count;

    public BFS(Node originNode){
        this.originNode = originNode;
        this.path = new HashMap<>();
        this.visited = new HashSet<>();
        this.count = new HashMap<>();

        this.doBFS();
    }

    private void doBFS(){

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(originNode);

        this.path.put(originNode,originNode);
        this.count.put(originNode,0);

        while(queue.size() != 0){
            Node node = queue.pop();
            this.visited.add(node);

            for(Edge neighbours : node.getNeighbours()){
                Node adjacentNode = neighbours.getDestinationNode();
                if (!this.visited.contains(adjacentNode)){
                    queue.add(adjacentNode);
                    this.path.put(adjacentNode,node);
                    Integer weightOfNode = this.count.get(node);
                    this.count.put(adjacentNode,weightOfNode + 1);
                }
            }
        }
    }

    public void printPath(Node destination){
        System.out.println("From : " + originNode.getNom());
        System.out.println("To : " + destination.getNom());
        System.out.println("Weight : " + count.get(destination));

        System.out.println(getPath(destination));
    }
}
