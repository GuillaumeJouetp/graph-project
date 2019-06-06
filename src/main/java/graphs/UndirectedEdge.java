package graphs;

import java.util.List;

public class UndirectedEdge extends Edge {


    private int encounters; // Pour compter le passage par les edges pour le clustering

    public UndirectedEdge(Node firstNode, Node secondNode) {
        this.sourceNode = firstNode;
        this.destinationNode = secondNode;
        this.encounters = 1;
    }

    public Node[] getNodes() {
        return new Node[]{sourceNode, destinationNode};
    }

    public int getEncounters() {
        return encounters;
    }

    private void incrementEncounter() {
        this.encounters++;
    }

    public String toString() {
        return "Between " + getNodes()[0].getNom() + " and " + getNodes()[1].getNom() + " (visited "+getEncounters()+" times)";
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
        return sourceNode == n1 && destinationNode == n2 || sourceNode == n2 && destinationNode == n1;
    }
}
