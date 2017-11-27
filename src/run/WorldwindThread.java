package run;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.symbology.TacticalSymbol;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import run.TacticalSymbols.*;
import static gov.nasa.worldwindx.examples.ApplicationTemplate.start;

public class WorldwindThread implements Runnable{
    private String exercise;
    private BlockingQueue<TacticalSymbol> symbolsQueue;

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
                throw new AssertionError("Exercise not recognised");


        }
        AppFrame world = (AppFrame) start("Open-CGF", AppFrame.class);
        WorldWindow wwd = world.getWwd();

        //Update tactical symbols
        while(true) {
            //cleans map of tactical symbols
            //world.symbolLayer.removeAllRenderables();

            int capacity = 100;

            //for all symbols give a standard set of attributes then add to the layer
            try {
                while(capacity > symbolsQueue.remainingCapacity() ){
                    TacticalSymbol symbol = symbolsQueue.take();
                    symbol.setAttributes(world.sharedAttrs);
                    symbol.setHighlightAttributes(world.sharedHighlightAttrs);
                    world.symbolLayer.addRenderable(symbol);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //tells map that renderables needs updating
            wwd.redraw();

            //adds delay to avoid processing overheads
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}

