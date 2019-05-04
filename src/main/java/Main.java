
import com.fasterxml.jackson.databind.ObjectMapper;
import graphs.RawGraph;

import java.io.*;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException {
        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        //convert json string to object
        RawGraph rawGraph = objectMapper.readValue(new URL("http://vasyenmetro.com/data/reseau.json"), RawGraph.class);
        rawGraph.createAgencyList();
        rawGraph.printEdges(rawGraph.getNode("1710"));

        //System.out.println("RawGraph Object\n"+ rawGraph);
    }
}
