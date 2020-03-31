import controller.ClientGenerator;
import controller.ParseInput;
import model.QueueList;
import model.Timer;
import view.OutputWriter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ParseInput parse = new ParseInput(args[0]);
        ClientGenerator allClient = new ClientGenerator(parse);
        Timer clock = new Timer(parse.getSimulationInterval());
        QueueList qList = new QueueList(parse.getNumberOfQueues());
        OutputWriter out = new OutputWriter(args[1], clock, allClient, qList, parse.getNumberOfClients());
        Thread tr = new Thread(out);
        tr.start();
    }
}
