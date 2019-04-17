package search;

import graphs.Graph;

import java.util.*;

public class Search<V> {

    /**
     * dfs functions
     * @param graph
     * @param start
     * @return
     */
    public List<Integer> dfs(Graph graph, Integer start){
        Stack<Integer> structure = new Stack<>();
        structure.add(start);
        List<Integer> visited = new ArrayList<>();

        do {
            Integer current_path = structure.pop();
            if (!visited.contains(current_path)){
                visited.add(current_path);
                List<Integer> neighbors = graph.neighbors(current_path);
                Collections.sort(neighbors);
                for (Integer neighbor : neighbors){
                    if (!visited.contains(neighbor)){
                        structure.add(neighbor);
                    }
                }
            }
        } while(structure.size() > 0);
        System.out.println("DFS :" + visited);
        return visited;

    }

    public boolean isConnected(Graph graph){
        if (dfs(graph,1).size() == graph.size()) {
            System.out.println("The Graph is connected");
            return true;
        }
        System.out.println("The Graph is not connected");
        return false;
    }

    /**
     * bfs functions
     * @param graph
     * @param start
     * @return
     */
    public List<Integer> bfs(Graph graph, Integer start){
        LinkedList<Integer> structure = new LinkedList<>();
        structure.add(start);
        List<Integer> visited = new ArrayList<>();

        do {
            Integer current_path = structure.pop();
            if (!visited.contains(current_path)){
                visited.add(current_path);
                for (Integer neighbor : graph.neighbors(current_path)){
                    if (!visited.contains(neighbor)){
                        structure.add(neighbor);
                    }
                }
            }
        } while(structure.size() > 0);
        System.out.println("BFS :" + visited);
        return visited;
    }
}
