package run;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.WorldWindow;
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

        WorldWindow wwd = world.getWwd();

        //Update tactical symbols
        while(true) {
            //cleans map of tactical symbols
            world.symbolLayer.removeAllRenderables();

            int capacity = 100;
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

            //tells map that renderables need updating
            wwd.redraw();


            //adds delay to avoid processing overheads
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}

