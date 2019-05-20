package search;

import graphs.Edge;
import graphs.Node;

import java.util.*;

// TODO Faire une class abstraite Search qu'implémentent BFS et Djikstra 
public class Djikstra {

    private Node originNode;
    private Map<Node, Node> previousNodes;
    private Set<Node> visited;

    public Djikstra(Node originNode){
        this.originNode = originNode;
        this.previousNodes = new HashMap<>();
        this.visited = new HashSet<>();

        this.doDjikstra();
    }

    private void doDjikstra(){

        Set<Node> queue = new HashSet<>();

        originNode.setDistance(0.);
        queue.add(originNode);
        this.previousNodes.put(originNode,originNode);
        while (queue.size() != 0) {
            Node currentNode = getLowestDistanceNode(queue);
            queue.remove(currentNode);
            for (Edge neighbour : currentNode.getNeighbours()) {
                Node adjacentNode = neighbour.getDestinationNode();
                Double edgeWeight = neighbour.getWeight();
                if (!this.visited.contains(adjacentNode)) {
                    setMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    queue.add(adjacentNode);
                    this.previousNodes.put(adjacentNode,currentNode);
                }
            }
            this.visited.add(currentNode);
        }
    }

    private static Node getLowestDistanceNode(Set <Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        Double lowestDistance = Double.MAX_VALUE;
        for (Node node: unsettledNodes) {
            Double nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void setMinimumDistance(Node evaluationNode, Double edgeWeigh, Node sourceNode) {
        Double sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
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
        System.out.println("Weight : " + destination.getDistance());

        System.out.println(getPath(destination));
    }
}
