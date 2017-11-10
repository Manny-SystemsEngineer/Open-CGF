package run;

//import edu.nps.moves.examples.PduSender;

import java.util.concurrent.TimeUnit;



public class Main {
    public static void main(String[] args) throws InterruptedException {
/*        String password = "manny";
        Scanner str_reader = new Scanner(System.in);
        System.out.println(("Login:"));
        String user = str_reader.next();

        if(!Objects.equals(user, password)){
            System.out.println("Access Denied!");
            System.exit(0);
        }

        System.out.println("Hello " + user + "!");
*/

        Thread worldwindthread = new Thread(new WorldwindThread());
        worldwindthread.start();

        System.out.println("Worldwind thread started");

        //PduSender Sender = new PduSender(80, "TEST");
        //Sender.run();

    }
}
