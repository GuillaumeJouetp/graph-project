package graphs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)

public class Route {

    private String[] arrets;
    private String type;
    private String ligne;

    public String getType(){
        return this.type;
    }

    public String getLigne(){
        return this.ligne;
    }

    public String toString(){
        return getType()+" "+getLigne();
    }

}
