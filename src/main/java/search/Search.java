package search;

import graphs.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public abstract class Search {
    Node originNode;
    Map<Node, Node> path;
    Set<Node> visited;

    ArrayList<Node> getPath(Node destination){
        ArrayList<Node> pathList = new ArrayList<>();
        pathList.add(destination);
        while (this.path.get(destination) != this.originNode){
            Node previousNode = this.path.get(destination);
            pathList.add(previousNode);
            destination = previousNode;
        }
        pathList.add(this.originNode);
        Collections.reverse(pathList);
        return (pathList);
    }


}
