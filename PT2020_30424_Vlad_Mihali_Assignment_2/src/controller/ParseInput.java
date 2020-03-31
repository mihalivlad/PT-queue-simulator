package controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ParseInput {
    private int numberOfClients;
    private int numberOfQueues;
    private int simulationInterval;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int minServiceTime;
    private int maxServiceTime;

    public ParseInput(String fileName) {
        try {
            File file = new File(fileName);
            Scanner in = new Scanner(file);
            numberOfClients = in.nextInt();
             numberOfQueues = in.nextInt();
             simulationInterval = in.nextInt();
             String str = in.next();
             minArrivalTime = Integer.parseInt(str.split(",")[0]);
             maxArrivalTime = Integer.parseInt(str.split(",")[1]);
            str = in.next();
             minServiceTime = Integer.parseInt(str.split(",")[0]);
             maxServiceTime = Integer.parseInt(str.split(",")[1]);
            in.close();
        } catch (InputMismatchException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }catch (NumberFormatException e) {
            System.out.println("Invalid Input");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public int getNumberOfQueues() {
        return numberOfQueues;
    }

    public int getSimulationInterval() {
        return simulationInterval;
    }

    public int getMinArrivalTime() {
        return minArrivalTime;
    }

    public int getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public int getMinServiceTime() {
        return minServiceTime;
    }

    public int getMaxServiceTime() {
        return maxServiceTime;
    }
}
