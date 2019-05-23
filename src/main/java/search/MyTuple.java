package search;

import graphs.Node;

public class MyTuple {
    private BFS bfs;
    private Node destinationNode;

    public MyTuple (BFS bfs, Node destinationNode){
        this.destinationNode = destinationNode;
        this.bfs = bfs;
    }

    public BFS getBfs() {
        return bfs;
    }

    public Node getDestinationNode() {
        return destinationNode;
    }
}
