package graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public abstract class Graph {

    int n; // graph order (number of nodes)
    int m; // grap size (number of edges)

    abstract void addEdge(Integer u, Integer v);

    public abstract List<Integer> neighbors(int v);

    abstract void degree(int v);

    public abstract void showGraph();

    /*q3. return the total order of the graph.*/
    int getN() {
        return n;
    }

    /*q3 bis. return the size of the graph.*/
    int getM() {
        return m;
    }

    public int size(){
        return this.m;
    }

    int getNfromFile(String fileName) throws IOException {

        int n = 0;

        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null){
            String[] row = line.split(" ");
            Integer linkTo = Integer.parseInt(row[0]);
            Integer toLink = Integer.parseInt(row[1]);
            if (n<linkTo) n=linkTo;
            if (n<toLink) n=toLink;
        }
        br.close();
        fr.close();
        return n;
    }
}
