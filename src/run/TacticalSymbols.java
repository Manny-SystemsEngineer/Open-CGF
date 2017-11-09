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

            TacticalSymbol airSymbol = new MilStd2525TacticalSymbol("SFAPMFQM-------", Position.fromDegrees(32.4520, 63.44553, 3000));
            airSymbol.setValue(AVKey.DISPLAY_NAME, "MIL-STD-2525 Friendly SOF Drone Aircraft");
            airSymbol.setAttributes(this.sharedAttrs);
            airSymbol.setHighlightAttributes(this.sharedHighlightAttrs);
            airSymbol.setModifier(SymbologyConstants.DIRECTION_OF_MOVEMENT, Angle.fromDegrees(235));
            airSymbol.setShowLocation(false);
            this.symbolLayer.addRenderable(airSymbol);

            TacticalSymbol targetSymbol = new MilStd2525TacticalSymbol("SHGPIB----A----", Position.fromDegrees(32.4014, 63.3894, 0));
            targetSymbol.setValue(AVKey.DISPLAY_NAME, "MIL-STD-2525 Hostile Facility");
            targetSymbol.setAttributes(this.sharedAttrs);
            targetSymbol.setHighlightAttributes(this.sharedHighlightAttrs);
            targetSymbol.setModifier(SymbologyConstants.DIRECTION_OF_MOVEMENT, Angle.fromDegrees(90));
            targetSymbol.setModifier(SymbologyConstants.SPEED_LEADER_SCALE, 0.5);
            targetSymbol.setShowLocation(false);
            this.symbolLayer.addRenderable(targetSymbol);

            TacticalSymbol groundSymbol = new MilStd2525TacticalSymbol("SFGPUCI----E---", Position.fromDegrees(32.423, 63.324, 0));
            groundSymbol.setValue(AVKey.DISPLAY_NAME, "MIL-STD-2525 Friendly Infantry Company");
            groundSymbol.setAttributes(this.sharedAttrs);
            groundSymbol.setHighlightAttributes(this.sharedHighlightAttrs);
            groundSymbol.setModifier(SymbologyConstants.DIRECTION_OF_MOVEMENT, Angle.fromDegrees(90));
            groundSymbol.setModifier(SymbologyConstants.SPEED_LEADER_SCALE, 0.5);
            groundSymbol.setShowLocation(false);
            this.symbolLayer.addRenderable(groundSymbol);

            TacticalSymbol reconSymbol = new MilStd2525TacticalSymbol("SFGPUCR----B---", Position.fromDegrees(32.413, 63.40, 0));
            reconSymbol.setValue(AVKey.DISPLAY_NAME, "MIL-STD-2525 Friendly Recon Squad");
            reconSymbol.setAttributes(this.sharedAttrs);
            reconSymbol.setHighlightAttributes(this.sharedHighlightAttrs);
            reconSymbol.setModifier(SymbologyConstants.DIRECTION_OF_MOVEMENT, Angle.fromDegrees(90));
            reconSymbol.setModifier(SymbologyConstants.SPEED_LEADER_SCALE, 0.5);
            reconSymbol.setShowLocation(false);
            this.symbolLayer.addRenderable(reconSymbol);



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
