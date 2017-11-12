package run;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.symbology.TacticalSymbol;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import run.TacticalSymbols.*;
import static gov.nasa.worldwindx.examples.ApplicationTemplate.start;

public class WorldwindThread implements Runnable{
    String exercise;
    BlockingQueue<TacticalSymbol> symbolsQueue;
    ArrayList<TacticalSymbol> symbolArrayList = new ArrayList<>();

    public WorldwindThread(String exerciseID, BlockingQueue<TacticalSymbol> symbolsQueue){
        exercise = exerciseID;
        this.symbolsQueue = symbolsQueue;
    }

    @Override
    public void run() {

        switch (exercise) {
            case "afghan-1":
                Configuration.setValue(AVKey.INITIAL_LATITUDE, 32.49);
                Configuration.setValue(AVKey.INITIAL_LONGITUDE, 63.455);
                Configuration.setValue(AVKey.INITIAL_HEADING, 22);
                Configuration.setValue(AVKey.INITIAL_PITCH, 82);
                Configuration.setValue(AVKey.INITIAL_ALTITUDE, 20000);
                break;
            case "mashhad-1":
                Configuration.setValue(AVKey.INITIAL_LATITUDE, 36.2257);
                Configuration.setValue(AVKey.INITIAL_LONGITUDE, 59.5767);
                Configuration.setValue(AVKey.INITIAL_HEADING, 22);
                Configuration.setValue(AVKey.INITIAL_PITCH, 82);
                Configuration.setValue(AVKey.INITIAL_ALTITUDE, 20000);
                break;
            default:
                System.out.println("Exercise not recognised");
                System.exit(0);
        }
       AppFrame world = (AppFrame) start("Open-CGF", AppFrame.class);

        //Update tactical symbols
        while(true) {

            int capacity = 100;
            ArrayList<TacticalSymbol> Symbols = new ArrayList<>();
            try {
                while(capacity > symbolsQueue.remainingCapacity() ){
                    TacticalSymbol symbol = symbolsQueue.take();
                    Symbols.add(symbol);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //cleans map of tactical symbols
            world.symbolLayer.removeAllRenderables();

            //for all symbols give a standard set of attributes then add to the layer
            for (TacticalSymbol Symbol : Symbols) {
                Symbol.setAttributes(world.sharedAttrs);
                Symbol.setHighlightAttributes(world.sharedHighlightAttrs);
                world.symbolLayer.addRenderable(Symbol);
            }

            //tells map that renderables need updating
            world.symbolLayer.firePropertyChange(AVKey.LAYER, null, this);

            //adds delay to avoid processing overheads
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}

