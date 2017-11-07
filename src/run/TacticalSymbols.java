package run;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.symbology.BasicTacticalSymbolAttributes;
import gov.nasa.worldwind.symbology.SymbologyConstants;
import gov.nasa.worldwind.symbology.TacticalSymbol;
import gov.nasa.worldwind.symbology.TacticalSymbolAttributes;
import gov.nasa.worldwind.symbology.milstd2525.MilStd2525TacticalSymbol;
import gov.nasa.worldwind.util.BasicDragger;
import gov.nasa.worldwind.util.WWUtil;
import gov.nasa.worldwindx.examples.ApplicationTemplate;

import java.awt.*;

public class TacticalSymbols extends ApplicationTemplate {
    public static class AppFrame extends ApplicationTemplate.AppFrame{
        protected RenderableLayer symbolLayer;
        protected TacticalSymbolAttributes sharedAttrs;
        protected TacticalSymbolAttributes sharedHighlightAttrs;
        protected BasicDragger dragger;

        public AppFrame() {
            this.symbolLayer = new RenderableLayer();
            this.symbolLayer.setName("Tactical Symbols");

            this.sharedAttrs = new BasicTacticalSymbolAttributes();
            this.sharedAttrs.setTextModifierMaterial(Material.RED);
            this.sharedHighlightAttrs = new BasicTacticalSymbolAttributes();
            this.sharedHighlightAttrs.setInteriorMaterial(Material.WHITE);
            this.sharedHighlightAttrs.setTextModifierMaterial(Material.WHITE);
            this.sharedHighlightAttrs.setOpacity(1.0);

            TacticalSymbol airSymbol = new MilStd2525TacticalSymbol("SFAPMFQM--GIUSA", Position.fromDegrees(32.4520, 63.44553, 3000));
            airSymbol.setValue(AVKey.DISPLAY_NAME, "MIL-STD-2525 Friendly SOF Drone Aircraft");
            airSymbol.setAttributes(this.sharedAttrs);
            airSymbol.setHighlightAttributes(this.sharedHighlightAttrs);
            airSymbol.setModifier(SymbologyConstants.DIRECTION_OF_MOVEMENT, Angle.fromDegrees(235));
            airSymbol.setShowLocation(false);
            this.symbolLayer.addRenderable(airSymbol);

            this.getWwd().getModel().getLayers().add(symbolLayer);

            this.dragger = new BasicDragger(this.getWwd());
            this.getWwd().addSelectListener(this.dragger);

            Dimension size = new Dimension(1800, 1000);
            this.setPreferredSize(size);
            this.pack();
            WWUtil.alignComponent(null, this, AVKey.CENTER);
        }





    }

}
