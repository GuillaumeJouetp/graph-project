package graphs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown=true)

public class Node {

    private String lat;
    private String lng;
    private Map<String, String[]> lignes;
    private String nom;
    private String num;
    private String type;
    private String isHub;

    public String getNom(){
        return this.nom;
    }

    public String getType(){
        return this.type;
    }

    public String getLat(){
        return this.lat;
    }

    public String getLng() {
        return this.lng;
    }

    public String toString(){
        return getNom()+" "+getType()+"\n";
    }


}
