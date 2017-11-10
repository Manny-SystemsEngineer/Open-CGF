package run;


import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.symbology.BasicTacticalSymbolAttributes;
import gov.nasa.worldwind.symbology.TacticalSymbol;
import gov.nasa.worldwind.symbology.TacticalSymbolAttributes;
import gov.nasa.worldwindx.examples.ApplicationTemplate;


import static gov.nasa.worldwindx.examples.ApplicationTemplate.start;

public class WorldwindThread implements Runnable{

    @Override
    public void run() {
        Configuration.setValue(AVKey.INITIAL_LATITUDE, 32.49);
        Configuration.setValue(AVKey.INITIAL_LONGITUDE, 63.455);
        Configuration.setValue(AVKey.INITIAL_HEADING, 22);
        Configuration.setValue(AVKey.INITIAL_PITCH, 82);
        Configuration.setValue(AVKey.INITIAL_ALTITUDE, 20000);

        start("Open-CGF", TacticalSymbols.AppFrame.class);

        //TEST

    }
}

