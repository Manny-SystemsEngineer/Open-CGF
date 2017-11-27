package run;

//import edu.nps.moves.examples.PduSender;

import gov.nasa.worldwind.symbology.TacticalSymbol;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<TacticalSymbol> tacticalSymbolsQueue = new ArrayBlockingQueue<TacticalSymbol>(100);

        //enter exerciseID
        Scanner str_reader = new Scanner(System.in);
        System.out.println(("Exercise:"));
        String exerciseID = str_reader.next();

        Runnable entity_drone = new EntityThread(tacticalSymbolsQueue,"SNAKE-EYES","SFAPMFQ--------", 32.4517, 63.4478, 5000.0,-0.0000000);
        new Thread(entity_drone).start();

        Runnable entity_sensor = new EntityThread(tacticalSymbolsQueue,"Hostile Radar","SHGPESR--------", 32.3858, 63.4099, 5000.0,0.0);
        new Thread(entity_sensor).start();

        Runnable entity_tanks = new EntityThread(tacticalSymbolsQueue,"Friendly Tanks","SFGPUCA----C---", 32.4517, 63.4478, 5000.0,-0.0000000);
        new Thread(entity_tanks).start();

        //pass exerciseID to worldwind thread then start thread
        Runnable worldwindthread = new WorldwindThread(exerciseID,tacticalSymbolsQueue);
        new Thread(worldwindthread).start();

        System.out.println("Worldwind thread started");

        //PduSender Sender = new PduSender(80, "TEST");
        //Sender.run();
    }
}
