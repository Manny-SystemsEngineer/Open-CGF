package run;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.symbology.SymbologyConstants;
import gov.nasa.worldwind.symbology.TacticalSymbol;
import gov.nasa.worldwind.symbology.milstd2525.MilStd2525TacticalSymbol;

public class GenerateSymbol {

//Input name, identifier and position to generate an icon at that location
    public static TacticalSymbol generateTacticalSymbol(String displayName, String SCID, double lat, double lon, double ele){
            TacticalSymbol newSymbol = new MilStd2525TacticalSymbol(SCID, Position.fromDegrees(lat, lon, ele));
            newSymbol.setValue(AVKey.DISPLAY_NAME, displayName);
            newSymbol.setModifier(SymbologyConstants.DIRECTION_OF_MOVEMENT, Angle.fromDegrees(25));
            newSymbol.setModifier(SymbologyConstants.SPEED_LEADER_SCALE, 0.5);
            newSymbol.setShowLocation(false);
            return newSymbol;
    }
}
