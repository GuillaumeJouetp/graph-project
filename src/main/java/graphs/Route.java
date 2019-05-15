package graphs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Route {

    private String direction;
    private String[] arrets;  // Ensemble des arrêts composants la route
    private String type;      // type des routes (meto/tram/RER/correspondance)
    private String ligne;     // numéro de la ligne (0 si correspondance)

    public String[] getArrets() {
        return arrets;
    }

    public String getType(){
        return this.type;
    }

    public String getLigne(){
        return this.ligne;
    }

    public String getDirection(){
        return this.direction;
    }

    public String toString(){
        return getType()+" "+getLigne()+" direction : "+getDirection() +"\n";
    }

}
