package graphs;

import java.util.*;

public class BFS {

    private Node originNode;
    private Map<Node, Node> previousNodes;
    private Map<Node, Boolean> visited;
    private Map<Node, Double> weight;

    public BFS(Node originNode){
        this.originNode = originNode;
        this.previousNodes = new HashMap<>();
        this.visited = new HashMap<>();
        this.weight = new HashMap<>();

        this.instantiatedBFS();
    }

    public void instantiatedBFS(){

        ArrayList<Node> queue = new ArrayList<>();
        queue.add(originNode);

        this.previousNodes.put(originNode,originNode);

        while(queue.size() != 0){

            Node node = queue.remove(0);
            this.visited.put(node, true);

            for(Edge neighbours:node.getNeighbourgs()){

                Node neighbourNode = neighbours.getDestinationNode();
                if (!this.visited.containsKey(neighbourNode)){
                    queue.add(neighbourNode);
                    this.previousNodes.put(neighbourNode,node);
                }

            }

        }

    }

    public ArrayList<Node> getPath(Node destination){
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

}
