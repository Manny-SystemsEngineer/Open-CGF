package run;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.layers.ViewControlsLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.symbology.BasicTacticalSymbolAttributes;
import gov.nasa.worldwind.symbology.TacticalSymbolAttributes;
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

        public AppFrame getAppFrame(){
            AppFrame appFrame = this;
            return appFrame;
        }

        public AppFrame() throws Exception {
            this.symbolLayer = new RenderableLayer();
            this.symbolLayer.setName("Tactical Symbols");

            this.sharedAttrs = new BasicTacticalSymbolAttributes();
            this.sharedAttrs.setTextModifierMaterial(Material.RED);
            this.sharedHighlightAttrs = new BasicTacticalSymbolAttributes();
            this.sharedHighlightAttrs.setInteriorMaterial(Material.WHITE);
            this.sharedHighlightAttrs.setTextModifierMaterial(Material.WHITE);
            this.sharedHighlightAttrs.setOpacity(1.0);

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

