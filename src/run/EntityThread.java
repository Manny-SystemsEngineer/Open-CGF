package run;

import gov.nasa.worldwind.symbology.TacticalSymbol;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class EntityThread implements Runnable{
    String entityThread;
    String entityName;
    String entitySIDC;
    double entitylat;
    double entitylon;
    double entityele;
    BlockingQueue<TacticalSymbol> entitySymbolsQueue;

    public EntityThread(String threadName, BlockingQueue<TacticalSymbol> symbolsQueue, String name, String SIDC, double lat, double lon, double ele){
        entityThread = threadName;
        entityName = name;
        entitySIDC = SIDC;
        entitylat = lat;
        entitylon = lon;
        entityele = ele;
        entitySymbolsQueue = symbolsQueue;


    }

    @Override
    public void run() {
        Thread.currentThread().setName(entityThread);
        //System.out.println(Thread.currentThread().getName());

        while (true) {
            try {
                TimeUnit.SECONDS.wait(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.entitylon += 0.0001;
            try {
                entitySymbolsQueue.put(GenerateSymbol.GenerateTacticalSymbol(entityName,entitySIDC,entitylat,entitylon,entityele));
                System.out.println("Generate TACSY");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
