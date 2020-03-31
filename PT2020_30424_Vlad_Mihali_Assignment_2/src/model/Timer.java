package model;

public class Timer implements Runnable {
    private int time;
    private int maxTime;

    public Timer(int maxTime) {
        this.maxTime = maxTime;
        time = 0;
    }

    public int getTime() {
        return time;
    }

    public void startTimer(){
        Thread tr = new Thread(this);
        tr.start();
    }

    public void stopTimer(){
        maxTime = -1;
    }

    @Override
    public String toString() {
        return "Time " + time;
    }

    @Override
    public void run() {
        while (time <= maxTime)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
        }
    }
}
