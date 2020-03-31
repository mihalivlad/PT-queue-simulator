package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QueueList {
    private List<QueueThread> queueList = new ArrayList<>();

    public QueueList(int size){
        for(int index = 1; index <= size; index++){
            queueList.add(new QueueThread(index));
        }
    }

    public void addClient(Client c){
        queueList.sort(Comparator.comparing(QueueThread::getWaiting));
        QueueThread qt = queueList.get(0);
        queueList.remove(0);
        qt.addClient(c);
        queueList.add(qt);
        queueList.sort(Comparator.comparing(QueueThread::getQueueId));
    }

    public boolean isEmpty(){
        for (QueueThread qt: queueList) {
            if(!qt.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public int clientsInQueues(){
        int size = 0;
        for (QueueThread qt: queueList) {
            size+=qt.size();
        }
        return size;
    }

    @Override
    public String toString() {
        String str = "";
        for (QueueThread qt: queueList) {
            str+= qt.toString() + "\n";
        }
        return str;
    }
}
