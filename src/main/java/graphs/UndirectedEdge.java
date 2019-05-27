package graphs;

import java.util.List;

public class UndirectedEdge {

    private Node firstNode;
    private Node secondNode;

    private int encounters; // Pour compter le passage par les edges pour le clustering

    public UndirectedEdge(Node firstNode, Node secondNode) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.encounters = 1;
    }

    public Node[] getNodes() {
        return new Node[]{firstNode, secondNode};
    }

    public int getEncounters() {
        return encounters;
    }

    private void incrementEncounter() {
        this.encounters++;
    }

    public String toString() {
        return "Between: " + getNodes()[0].getNom() + " and: " + getNodes()[1].getNom() + "\n";
    }

    public static void addEdgesFromPath(List<UndirectedEdge> edges, List<Node> path) {
        for (int i = 0; i < path.size() - 2; i++) {
            Node n1 = path.get(i);
            Node n2 = path.get(i + 1);
            boolean foundEdge = false;
            for (UndirectedEdge edge : edges) {
                if (edge.hasNodes(n1, n2)) {
                    edge.incrementEncounter();
                    foundEdge = true;
                    break;
                }
            }
            if (!foundEdge) {
                edges.add(new UndirectedEdge(n1, n2));
            }
        }
    }

    private boolean hasNodes(Node n1, Node n2) {
        return firstNode == n1 && secondNode == n2 || firstNode == n2 && secondNode == n1;
    }
}
