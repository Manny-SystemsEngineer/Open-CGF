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

        Thread thread = new Thread(new WorldwindThread());
        thread.start();

        //PduSender Sender = new PduSender(80, "TEST");
        //Sender.run();

        System.out.println("WOOOOOOO!");

        try {
            TimeUnit.SECONDS.sleep(20);
            //System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
