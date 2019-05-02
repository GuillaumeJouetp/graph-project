
import com.fasterxml.jackson.databind.ObjectMapper;
import graphs.RawGraph;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class Main {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static byte[] readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            byte[] jsonData = jsonText.getBytes();
            return jsonData;
        } finally {
            is.close();
        }
    }

    public static void main(String[] args) throws IOException {
        byte[] jsonData = readJsonFromUrl("http://vasyenmetro.com/data/reseau.json");
        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        //convert json string to object
        RawGraph rawGraph = objectMapper.readValue(jsonData, RawGraph.class);
        System.out.println("RawGraph Object\n"+ rawGraph);
    }
}
