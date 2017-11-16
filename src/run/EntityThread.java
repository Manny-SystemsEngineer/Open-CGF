package run;

import gov.nasa.worldwind.symbology.TacticalSymbol;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class EntityThread implements Runnable{
    String entityName;
    String entitySIDC;
    double entitylat;
    double entitylon;
    double entityele;
    double entityMov;
    boolean entityAli;
    BlockingQueue<TacticalSymbol> entitySymbolsQueue;

    public EntityThread( BlockingQueue<TacticalSymbol> symbolsQueue, String name, String SIDC, double lat, double lon, double ele, double Mov){
        this.entityName = name;
        this.entitySIDC = SIDC;
        this.entitylat = lat;
        this.entitylon = lon;
        this.entityele = ele;
        this.entityMov = Mov;
        this.entitySymbolsQueue = symbolsQueue;
        this.entityAli = true;


    }

    @Override
    public void run() {

        while (true) {
            if(!this.entityAli){
                this.entitySIDC = this.entitySIDC.substring(0,4)+'A'+this.entitySIDC.substring(5);
            }

            this.entitylon += entityMov;
            this.entitylat += entityMov;
            TacticalSymbol symbol = GenerateSymbol.GenerateTacticalSymbol(entityName,entitySIDC,entitylat,entitylon,entityele);

            try {
                entitySymbolsQueue.put(symbol);
            } catch (InterruptedException e) {
                System.out.println(e);
                e.printStackTrace();
            }

        }
    }

}
