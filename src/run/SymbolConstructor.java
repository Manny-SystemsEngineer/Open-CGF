package run;

import gov.nasa.worldwind.symbology.TacticalSymbol;

import java.util.ArrayList;

public class SymbolConstructor {
    //create an array to store tacical symbols
    static ArrayList<TacticalSymbol> Symbols = new ArrayList<>();

    public static ArrayList<TacticalSymbol> getSymbol() throws Exception {
        populateSymbols();
        return Symbols;
    }

    private static void populateSymbols() throws Exception {


        //generate BLUFOR symbols and add to the Symbols array
        Symbols.add(GenerateSymbol.GenerateTacticalSymbol("Friendly Done", "SFAPMFQ--------", 32.4517, 63.4478, 5000.0));
        Symbols.add(GenerateSymbol.GenerateTacticalSymbol("Friendly Recon Squad", "SFGPUCR----B---", 32.4324, 63.4146, 3000.0));
        Symbols.add(GenerateSymbol.GenerateTacticalSymbol("Friendly Infantry Company", "SFGPUCI----E---", 32.3929, 63.3547, 0.0));

        //generate OPFOR symbols and add to the Symbols array
        Symbols.add(GenerateSymbol.GenerateTacticalSymbol("Hostile HQ", "SHGPIB----A----", 32.3858, 63.4099, 3000.0));
        Symbols.add(GenerateSymbol.GenerateTacticalSymbol("Potential Hostile Missle Launcher", "SHGAUCM--------", 32.39, 63.45, 3000.0));
    }
}
