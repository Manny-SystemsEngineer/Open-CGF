package run;


import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.symbology.TacticalSymbol;



import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import run.TacticalSymbols.*;

import static gov.nasa.worldwindx.examples.ApplicationTemplate.start;

public class WorldwindThread implements Runnable{

    @Override
    public void run() {
        Configuration.setValue(AVKey.INITIAL_LATITUDE, 32.49);
        Configuration.setValue(AVKey.INITIAL_LONGITUDE, 63.455);
        Configuration.setValue(AVKey.INITIAL_HEADING, 22);
        Configuration.setValue(AVKey.INITIAL_PITCH, 82);
        Configuration.setValue(AVKey.INITIAL_ALTITUDE, 20000);

       AppFrame world = (AppFrame) start("Open-CGF", AppFrame.class);

        //Update tactical symbols
        while(true) {
            ArrayList<TacticalSymbol> Symbols = SymbolConstructor.getSymbol();

            //for all symbols give a standard set of attributes then add to the layer
            for (TacticalSymbol Symbol : Symbols) {
                Symbol.setAttributes(world.sharedAttrs);
                Symbol.setHighlightAttributes(world.sharedHighlightAttrs);
                world.symbolLayer.addRenderable(Symbol);
            }

            world.symbolLayer.firePropertyChange(AVKey.LAYER, null, this);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

