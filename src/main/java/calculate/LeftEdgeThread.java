package calculate;

import java.util.ArrayList;
import java.util.List;

public class LeftEdgeThread extends KochThread{
    private KochFractal kochfractal;
    private KochManager kochmanager;
    private int nxt;

    public LeftEdgeThread(KochManager kochManager, int nxt) {
        this.kochmanager = kochManager;
        this.nxt = nxt;
        this.kochfractal = new KochFractal(this);
    }

    @Override
    public void run() {
        kochfractal.setLevel(nxt);
        kochfractal.generateLeftEdge();

        System.out.println("left";
    }

    public void addEdges() {
        kochmanager.addEdge(edges);
    }
}
