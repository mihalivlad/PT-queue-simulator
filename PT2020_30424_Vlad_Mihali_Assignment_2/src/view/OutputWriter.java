package view;

import controller.ClientGenerator;
import model.QueueList;
import model.Timer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputWriter implements Runnable {
    private String outFile;
    private Timer clock;
    private ClientGenerator allClient;
    private QueueList qList;
    private int numberOfClients;

    public OutputWriter(String outFile, Timer clock, ClientGenerator allClient, QueueList qList, int numberOfClients) {
        this.outFile = outFile;
        this.clock = clock;
        this.allClient = allClient;
        this.qList = qList;
        this.numberOfClients = numberOfClients;
    }

    @Override
    public void run() {
        double avgTime = 0;
        try {
            BufferedWriter fileOut = new BufferedWriter(new FileWriter(outFile));
        Thread tr = new Thread(clock);
        tr.start();
        allClient.sortByArrivalTime();
        avgTime += qList.clientsInQueues();
        fileOut.write(clock.toString()+"\nWaiting clients: " + allClient.toString()+"\n"+qList.toString());
        //System.out.println(clock.toString());
        //System.out.println("Waiting clients: " + allClient.toString());
        //System.out.println(qList.toString());
            Thread.sleep(1000);
            while (tr.isAlive()) {
                Thread.sleep(1);
                while (!allClient.isEmpty()) {
                    if (allClient.getFirst().getArrivalTime() > clock.getTime()) {
                        break;
                    }
                    qList.addClient(allClient.extractFirst());

                }
                if (allClient.isEmpty() && qList.isEmpty()) {
                    clock.stopTimer();
                }
                avgTime += qList.clientsInQueues();
                fileOut.write(clock.toString()+"\nWaiting clients: " + allClient.toString()+"\n"+qList.toString());
                //System.out.println(clock.toString());
                //System.out.println("Waiting clients: " + allClient.toString());
                //System.out.println(qList.toString());
                Thread.sleep(999);
            }
            avgTime /= numberOfClients;
            fileOut.write("Average waiting time: " + avgTime);
            //System.out.println("Average waiting time: " + avgTime);
            fileOut.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
    }
    }

}
