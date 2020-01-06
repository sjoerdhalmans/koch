package calculate;

public class BottomEdgeThread extends KochThread{
    private KochFractal kochfractal;
    private KochManager kochmanager;
    private int nxt;

    public BottomEdgeThread(KochManager kochManager, int nxt) {
        this.kochmanager = kochManager;
        this.nxt = nxt;
        this.kochfractal = new KochFractal(this);
    }

    @Override
    public void run() {
        kochfractal.setLevel(nxt);
        kochfractal.generateBottomEdge();

        System.out.println("bottom");
    }

    public void addEdges() {
        kochmanager.addEdge(edges);
    }
}
