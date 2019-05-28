package search;

public class MyTuple<X, Y> {

    public final X x;
    public final Y y;

    public MyTuple(X x, Y y) {
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
