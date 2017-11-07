package run;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwindx.examples.ApplicationTemplate;

import javax.swing.*;
import java.awt.*;

public class Worldwind_Gen extends JFrame{
    public Worldwind_Gen()
    {
        WorldWindowGLCanvas wwd = new WorldWindowGLCanvas();
        wwd.setPreferredSize(new java.awt.Dimension(1000,800));
        this.getContentPane().add(wwd, BorderLayout.CENTER);
        wwd.setModel(new BasicModel());
    }
}


