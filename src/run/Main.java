package run;

//import edu.nps.moves.examples.PduSender;


import gov.nasa.worldwind.symbology.TacticalSymbol;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<TacticalSymbol> tacticalSymbolsQueue = new ArrayBlockingQueue<TacticalSymbol>(100);
        BlockingQueue<ArrayList<TacticalSymbol>> tacticalSybmolsArrayQueue = new ArrayBlockingQueue<ArrayList<TacticalSymbol>>(100);


        //enter exerciseID
        Scanner str_reader = new Scanner(System.in);
        System.out.println(("Exercise:"));
        String exerciseID = str_reader.next();

        Runnable entity_drone = new EntityThread("drone_entity",tacticalSymbolsQueue,"SNAKE-EYES","SFAPMFQ--------", 32.4517, 63.4478, 5000.0);
        new Thread(entity_drone).start();

        //start symbolConstructorthread
        Runnable symbolConstructorThread = new SymbolConstructorThread(tacticalSymbolsQueue,tacticalSybmolsArrayQueue);
        new Thread(symbolConstructorThread).start();

        System.out.println("SymbolConstructor thread started");

        //pass exerciseID to worldwind thread then start thread
        Runnable worldwindthread = new WorldwindThread(exerciseID,tacticalSybmolsArrayQueue);
        new Thread(worldwindthread).start();

        System.out.println("Worldwind thread started");

        //PduSender Sender = new PduSender(80, "TEST");
        //Sender.run();

        System.out.println(tacticalSymbolsQueue);


    }
}
