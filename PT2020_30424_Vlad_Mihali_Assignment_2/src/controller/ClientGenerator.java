package controller;

import model.Client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ClientGenerator {
    private List<Client> clientList = new ArrayList<Client>();
    public ClientGenerator(ParseInput data){
        for(int index = 1; index <= data.getNumberOfClients(); index++){
            Random r = new Random();
            int arrivalTime = r.nextInt(data.getMaxArrivalTime() - data.getMinArrivalTime()) + data.getMinArrivalTime();
            int serviceTime = r.nextInt(data.getMaxServiceTime() - data.getMinServiceTime()) + data.getMinServiceTime();
            clientList.add(new Client(index, arrivalTime, serviceTime));
        }
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public Client getFirst(){
        return clientList.get(0);
    }

    public Client extractFirst(){
        Client c = clientList.get(0);
        clientList.remove(0);
        c.setServiceTime(c.getServiceTime());
        return c;
    }

    public void sortByID(){
        clientList.sort(Comparator.comparing(Client::getId));
    }

    public void sortByArrivalTime(){
        clientList.sort(Comparator.comparing(Client::getArrivalTime));
    }

    public void sortByServiceTime(){
        clientList.sort(Comparator.comparing(Client::getServiceTime));
    }

    public boolean isEmpty(){
        return clientList.isEmpty();
    }

    @Override
    public String toString() {
        String str = "";
        if(isEmpty())
            return str;
        for (Client c: clientList) {
            str += c.toString() + ";";
        }
        str = str.substring(0,str.length()-1);
        return str;
    }
}
