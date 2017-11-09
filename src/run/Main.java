package run;

//import edu.nps.moves.examples.PduSender;


import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;

import javax.swing.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static gov.nasa.worldwindx.examples.ApplicationTemplate.start;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        String password = "manny";
        Scanner str_reader = new Scanner(System.in);
        System.out.println(("Login:"));
        String user = str_reader.next();

        if(!Objects.equals(user, password)){
            System.out.println("Access Denied!");
            System.exit(0);
        }

        System.out.println("Hello " + user + "!");


        //PduSender Sender = new PduSender(80, "TEST");
        //Sender.run();

        Configuration.setValue(AVKey.INITIAL_LATITUDE, 32.49);
        Configuration.setValue(AVKey.INITIAL_LONGITUDE, 63.455);
        Configuration.setValue(AVKey.INITIAL_HEADING, 22);
        Configuration.setValue(AVKey.INITIAL_PITCH, 82);
        Configuration.setValue(AVKey.INITIAL_ALTITUDE, 20000);

        start("Worldwind Tactical Symbols", TacticalSymbols.AppFrame.class);

        try {
            TimeUnit.SECONDS.sleep(20);
            //System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//just one aircraft ATM
    }
}
