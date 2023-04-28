package org.example.Homework;

public class TimeKeeper extends Thread {
    private volatile boolean stop = false;
    private final Exploration explore;
    private final long timeLimitInMillis = 30000; // 30 seconds

    public TimeKeeper(Exploration explore) {
        this.explore = explore;
        setDaemon(true);
    }

    public void stopTimer() {
        stop = true;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (!stop) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= timeLimitInMillis) {
                System.out.println("Time limit exceeded.");
                explore.stopAllRobots();
                stop = true;
            } else {
                //System.out.println("Elapsed time: " + elapsedTime / 1000 + " seconds");
            }
            try {
                Thread.sleep(1000); // sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
