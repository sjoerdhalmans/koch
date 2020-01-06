
import org.junit.Test;
import calculate.KochManager;
import calculate.LeftEdgeThread;
import calculate.RightEdgeThread;
import calculate.BottomEdgeThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;
import static org.junit.Assert.assertNotEquals;


public class calculate {


    @Test
    public void testThreadsSuccesful() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future> futures = new ArrayList<>();
        KochManager kochManager = new KochManager(null);
        LeftEdgeThread leftEdgeThread = new LeftEdgeThread(kochManager, 12);
        RightEdgeThread rightEdgeThread = new RightEdgeThread(kochManager, 12);
        BottomEdgeThread bottomEdgeThread = new BottomEdgeThread(kochManager, 12);

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

        leftEdgeThread.addEdges();
        rightEdgeThread.addEdges();
        bottomEdgeThread.addEdges();

        assertEquals(12582912, kochManager.getEdges().size());
    }

    @Test
    public void testThreadsUnsuccessful() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future> futures = new ArrayList<>();
        KochManager kochManager = new KochManager(null);
        LeftEdgeThread leftEdgeThread = new LeftEdgeThread(kochManager, 12);
        RightEdgeThread rightEdgeThread = new RightEdgeThread(kochManager, 12);

        futures.add(executor.submit(leftEdgeThread));
        futures.add(executor.submit(rightEdgeThread));

        try {
            for (Future future : futures) {
                future.get();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        leftEdgeThread.addEdges();
        rightEdgeThread.addEdges();

        assertNotEquals(12582912, kochManager.getEdges().size());
    }

}