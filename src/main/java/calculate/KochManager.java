/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import fun3kochfractalfx.FUN3KochFractalFX;
import timeutil.TimeStamp;

/**
 *
 * @author Nico Kuijpers
 * Modified for FUN3 by Gertjan Schouten
 */
public class KochManager {

    private ArrayList<Edge> edges;
    private FUN3KochFractalFX application;
    private TimeStamp tsCalc;
    private TimeStamp tsDraw;
    private LeftEdgeThread leftEdgeThread;
    private RightEdgeThread rightEdgeThread;
    private BottomEdgeThread bottomEdgeThread;
    private int nrOfEdges = 0;
    
    public KochManager(FUN3KochFractalFX application) {
        this.edges = new ArrayList<Edge>();
        this.application = application;
        this.tsCalc = new TimeStamp();
        this.tsDraw = new TimeStamp();
    }
    
    public void changeLevel(int nxt) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future> futures = new ArrayList<>();
        this.leftEdgeThread = new LeftEdgeThread(this, nxt);
        this.rightEdgeThread = new RightEdgeThread(this, nxt);
        this.bottomEdgeThread = new BottomEdgeThread(this, nxt);

        edges.clear();
        tsCalc.init();
        tsCalc.setBegin("Begin calculating");

        futures.add(executor.submit(leftEdgeThread));
        futures.add(executor.submit(rightEdgeThread));
        futures.add(executor.submit(bottomEdgeThread));

        try {
            for (Future future : futures) {
                future.get();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        tsCalc.setEnd("End calculating");

        leftEdgeThread.addEdges();
        rightEdgeThread.addEdges();
        bottomEdgeThread.addEdges();

        application.setTextNrEdges("" + this.edges.size());
        application.setTextCalc(tsCalc.toString());
        drawEdges();
    }
    
    public void drawEdges() {
        tsDraw.init();
        tsDraw.setBegin("Begin drawing");
        application.clearKochPanel();
        for (Edge e : edges) {
            application.drawEdge(e);
        }
        tsDraw.setEnd("End drawing");
        application.setTextDraw(tsDraw.toString());
    }
    
    public void addEdge(List<Edge> e) {
        this.edges.addAll(e);
    }
}
