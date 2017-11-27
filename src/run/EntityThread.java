package run;

import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.symbology.TacticalSymbol;
import java.util.concurrent.BlockingQueue;

public class EntityThread implements Runnable{
    private String entityName;
    private String entitySIDC;
    private double entitylat;
    private double entitylon;
    private double entityele;
    private double entityMov;
    private boolean entityAli;
    private BlockingQueue<TacticalSymbol> entitySymbolsQueue;
    private TacticalSymbol symbol;

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

        this.symbol = GenerateSymbol.generateTacticalSymbol(entityName,entitySIDC,entitylat,entitylon,entityele);

        while (true) {
            if(!this.entityAli){
                this.entitySIDC = this.entitySIDC.substring(0,4)+'A'+this.entitySIDC.substring(5);
            }

            this.entitylon += entityMov;
            this.entitylat += entityMov;
            this.symbol.setPosition(Position.fromDegrees(this.entitylat, this.entitylon, this.entityele));

            try {
                entitySymbolsQueue.put(symbol);
            } catch (InterruptedException e) {
                System.out.println(e);
                e.printStackTrace();
            }

        }
    }

}
