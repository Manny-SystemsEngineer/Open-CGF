package run;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.layers.ViewControlsLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.symbology.BasicTacticalSymbolAttributes;
import gov.nasa.worldwind.symbology.SymbologyConstants;
import gov.nasa.worldwind.symbology.TacticalSymbol;
import gov.nasa.worldwind.symbology.TacticalSymbolAttributes;
import gov.nasa.worldwind.symbology.milstd2525.MilStd2525TacticalSymbol;
import gov.nasa.worldwind.util.BasicDragger;
import gov.nasa.worldwind.util.WWUtil;
import gov.nasa.worldwindx.examples.ApplicationTemplate;
import jdk.nashorn.internal.ir.Symbol;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class TacticalSymbols extends ApplicationTemplate {
    public static class AppFrame extends ApplicationTemplate.AppFrame{
        protected RenderableLayer symbolLayer;
        protected TacticalSymbolAttributes sharedAttrs;
        protected TacticalSymbolAttributes sharedHighlightAttrs;
        protected BasicDragger dragger;



        public AppFrame() throws Exception {
            this.symbolLayer = new RenderableLayer();
            this.symbolLayer.setName("Tactical Symbols");

            this.sharedAttrs = new BasicTacticalSymbolAttributes();
            this.sharedAttrs.setTextModifierMaterial(Material.RED);
            this.sharedHighlightAttrs = new BasicTacticalSymbolAttributes();
            this.sharedHighlightAttrs.setInteriorMaterial(Material.WHITE);
            this.sharedHighlightAttrs.setTextModifierMaterial(Material.WHITE);
            this.sharedHighlightAttrs.setOpacity(1.0);

            //create an array to store tacical symbols
            ArrayList<TacticalSymbol> Symbols = new ArrayList<>();

            //generate BLUFOR symbols and add to the Symbols array
            Symbols.add(GenerateSymbol.GenerateTacticalSymbol("Friendly Done","SFAPMFQ--------",32.4517,63.4478,5000.0));
            Symbols.add(GenerateSymbol.GenerateTacticalSymbol("Friendly Recon Squad","SFGPUCR----B---",32.4324,63.4146,3000.0));
            Symbols.add(GenerateSymbol.GenerateTacticalSymbol("Friendly Infantry Company","SFGPUCI----E---",32.3929,63.3547,0.0));

            //generate OPFOR symbols and add to the Symbols array
            Symbols.add(GenerateSymbol.GenerateTacticalSymbol("Hostile HQ","SHGPIB----A----",32.3858,63.4099,3000.0));
            Symbols.add(GenerateSymbol.GenerateTacticalSymbol("Potential Hostile Missle Launcher","SHGAUCM--------",32.39,63.45,3000.0));

            //for all symbols give a standard set of attributes then add to the layer
            for(TacticalSymbol Symbol : Symbols){
                Symbol.setAttributes(this.sharedAttrs);
                Symbol.setHighlightAttributes(this.sharedHighlightAttrs);
                this.symbolLayer.addRenderable(Symbol);
            }



            this.getWwd().getModel().getLayers().add(symbolLayer);

            this.dragger = new BasicDragger(this.getWwd());
            this.getWwd().addSelectListener(this.dragger);

            ViewControlsLayer vlayer = (ViewControlsLayer) this.getWwd().getModel().getLayers().getLayersByClass((ViewControlsLayer.class)).get(0);
            vlayer.setShowFovControls(false);
            vlayer.setShowHeadingControls(false);
            vlayer.setShowLookControls(false);
            vlayer.setShowPanControls(false);
            vlayer.setShowPitchControls(false);
            vlayer.setShowVeControls(false);
            vlayer.setShowZoomControls(false);
            vlayer.setName("Open-CGF");


            Dimension size = new Dimension(1800, 1000);
            this.setPreferredSize(size);

            this.pack();
            WWUtil.alignComponent(null, this, AVKey.CENTER);


        }





    }

}
