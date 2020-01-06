package calculate;

public class RightEdgeThread extends KochThread{
    private KochFractal kochfractal;
    private KochManager kochmanager;
    private int nxt;

    public RightEdgeThread(KochManager kochManager, int nxt) {
        this.kochmanager = kochManager;
        this.nxt = nxt;
        this.kochfractal = new KochFractal(this);
    }

    @Override
    public void run() {
        kochfractal.setLevel(nxt);
        kochfractal.generateRightEdge();

        System.out.println("right");
    }

    public void addEdges() {
        kochmanager.addEdge(edges);
    }
}
