package model;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueThread implements Runnable {
    private int queueId;
    private boolean isRunning;
    private int waiting;
    private Queue<Client> clientQueue = new LinkedList<>();

    public QueueThread(int queueId) {
        this.queueId = queueId;
        this.isRunning = false;
        this.waiting = 0;
    }
    public int getQueueId() {
        return queueId;
    }

    public void startRunning() {
        isRunning = true;
        Thread tr = new Thread(this);
        tr.start();
    }

    @Override
    public void run() {
        while(isRunning){
            try {
                Thread.sleep(998);
                Client first = clientQueue.peek();
                first.decrementServiceTime();
                waiting--;
                if(first.getServiceTime() <= 0){
                    clientQueue.remove();
            }
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(clientQueue.isEmpty()){
                isRunning = false;
            }
        }
    }

    @Override
    public String toString() {
        String str = "Queue " + queueId + ":";
        if(clientQueue.isEmpty()){
            str += "closed";
        }else {
            for (Client c : clientQueue) {
                str += c.toString() + ";";
            }
        }
        return str;
    }

    public int getWaiting() {
        return waiting;
    }

    public boolean isEmpty(){
        return clientQueue.isEmpty();
    }

    public int size(){
        return clientQueue.size();
    }

    public void addClient(Client c){
        clientQueue.add(c);
        waiting+=c.getServiceTime();
        if(size() == 1){
            startRunning();
        }
    }
}

