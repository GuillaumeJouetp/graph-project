package search;

import graphs.Edge;
import graphs.Graph;
import graphs.Node;

import java.util.*;

public class Djikstra extends Search{

    private Map<Node,Double> nodeWeights;

    public Djikstra(Graph graph, String originNode){
        this.originNode = graph.getNode(originNode);
        this.path = new HashMap<>();
        this.visited = new HashSet<>();
        this.nodeWeights = new HashMap<>();

        this.initializeWeights(graph);
        this.doDjikstra();
    }

    private void initializeWeights(Graph graph) {
        for (Node node : graph.getListStations()){
            this.nodeWeights.put(node,Double.MAX_VALUE);
        }
    }

    private void doDjikstra(){

        Set<Node> queue = new HashSet<>();

        this.nodeWeights.put(originNode,0.);
        queue.add(originNode);
        this.path.put(originNode,originNode);
        while (queue.size() != 0) {
            Node currentNode = getLowestWeightNode(queue);
            queue.remove(currentNode);
            for (Edge neighbour : currentNode.getNeighbours()) {
                Node adjacentNode = neighbour.getDestinationNode();
                Double edgeWeight = neighbour.getWeight();
                if (!this.visited.contains(adjacentNode)) {
                    setMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    queue.add(adjacentNode);
                    this.path.put(adjacentNode,currentNode);
                }
            }
            this.visited.add(currentNode);
        }
    }

    private Node getLowestWeightNode(Set <Node> unsettledNodes) {
        Node lowestWeightNode = null;
        Double lowestWeight = Double.MAX_VALUE;
        for (Node node: unsettledNodes) {
            Double nodeWeight = this.nodeWeights.get(node);
            if (nodeWeight < lowestWeight) {
                lowestWeight = nodeWeight;
                lowestWeightNode = node;
            }
        }
        return lowestWeightNode;
    }

    private void setMinimumDistance(Node evaluationNode, Double edgeWeigh, Node sourceNode) {
        Double sourceWeight = this.nodeWeights.get(sourceNode);
        if (sourceWeight + edgeWeigh < this.nodeWeights.get(evaluationNode)) {
            this.nodeWeights.put(evaluationNode, sourceWeight + edgeWeigh);
        }
    }

    public void printPath(Node destination){
        System.out.println("From : " + originNode.getNom());
        System.out.println("To : " + destination.getNom());
        System.out.println("Weight : " + this.nodeWeights.get(destination));

        System.out.println(getPath(destination));
    }
}
