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

        //create simulation entities
        Runnable entity_drone = new EntityThread(tacticalSymbolsQueue,"SNAKE-EYES","SFAPMFQ--------", 32.4517, 63.4478, 5000.0,-0.000005);
        new Thread(entity_drone).start();

        Runnable entity_infantry = new EntityThread(tacticalSymbolsQueue,"FOXHOUND","SFGPUCI----E---", 32.4325, 63.4146, 0.0,0.0);
        new Thread(entity_infantry).start();

        Runnable entity_armour = new EntityThread(tacticalSymbolsQueue,"IRONSIDE","SFGPUCA----D---", 32.3929, 63.3547, 0.0,0.0000001);
        new Thread(entity_armour).start();

        Runnable entity_sensor = new EntityThread(tacticalSymbolsQueue,"Hostile Radar","SHGPESR--------", 32.3858, 63.4099, 0.0,0.0);
        new Thread(entity_sensor).start();

        //pass exerciseID to worldwind thread then start thread
        Runnable worldwindthread = new WorldwindThread(exerciseID,tacticalSymbolsQueue);
        new Thread(worldwindthread).start();

        System.out.println("Worldwind thread started");

        //PduSender Sender = new PduSender(80, "TEST");
        //Sender.run();
    }
}
