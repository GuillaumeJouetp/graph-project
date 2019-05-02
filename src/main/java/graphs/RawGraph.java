package graphs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown=true)

public class RawGraph {

    private Map<String, Node> stations;
    private Route[] routes;

    public Route[] getRoutes(){
        return this.routes;
    }

    public Map<String, Node> getStations(){
        return this.stations;
    }

    public ArrayList<Node> getListStations(){
        Set keys = this.stations.keySet();
        ArrayList<Node> listStation = new ArrayList<>();
        for (Object key: keys){
            listStation.add(this.stations.get(key));
        }
        return listStation;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("***** Raw Graph Details *****\n");
        sb.append("Routes = "+Arrays.toString(getRoutes())+"\n");
        sb.append("Stations = "+getListStations()+"\n");

        return sb.toString();
    }

}
