package calculate;

import java.util.ArrayList;
import java.util.List;

public class KochThread extends Thread {
    protected int nxt;
    protected List<Edge> edges = new ArrayList<>();

    public int getNxt() {
        return nxt;
    }

    public void setNxt(int nxt) {
        this.nxt = nxt;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }
}
