package utils;

public class Tuple<X, Y> {

    public final X x;
    public final Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getSearchMethod() {
        return x;
    }

    public Y getDestinationNode() {
        return y;
    }
}
