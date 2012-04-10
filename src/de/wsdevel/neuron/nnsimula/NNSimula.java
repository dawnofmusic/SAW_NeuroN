package de.wsdevel.neuron.nnsimula;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import de.wsdevel.neuron.backpropagation.Net;
import de.wsdevel.neuron.backpropagation.example.Patterns;
import de.wsdevel.neuron.backpropagation.ui.NetControlPanel;
import de.wsdevel.neuron.backpropagation.ui.NetPropertiesPanel;
import de.wsdevel.neuron.backpropagation.ui.PatternPanel;
import de.wsdevel.neuron.backpropagation.ui.System;
import de.wsdevel.tools.awt.GBC;
import de.wsdevel.tools.awt.GUIToolkit;

/**
 * Created on 09.04.2012 for project: SAW_NeuroN
 * 
 * (c) 2012 Sebastian A. Weiﬂ - All rights reserved.
 * 
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 */
public class NNSimula {

    /**
     * COMMENT.
     * 
     * @param args
     */
    public static void main(String[] args) {
	BasicConfigurator.configure();
	Logger.getRootLogger().setLevel(Level.WARN);

	System.instance.setPatternToBeTrained(Patterns.EXAMPLE_PATTERNS[0]);
	System.instance.setNet(Net.createNet(2, 1, 32, 1));

	JFrame jFrame = new JFrame("NNSimula 2012");
	jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	jFrame.addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
		java.lang.System.exit(0);
	    }
	});

	JPanel mainPanel = new JPanel(new GridBagLayout());
	mainPanel.add(new NetPropertiesPanel(System.instance.getNet()),
		new GBC().pos(0, 0).fillHorizontal(1));
	mainPanel.add(new NetControlPanel(System.instance.getNet()), new GBC()
		.pos(1, 0).fillHorizontal(1));
	mainPanel.add(new PatternPanel(), new GBC().pos(0, 1)
		.gridWidthRemainder().fillBoth(1, 1));

	jFrame.getContentPane().add(mainPanel);

	jFrame.setSize(new Dimension(800, 600));
	GUIToolkit.center(jFrame);
	jFrame.setVisible(true);
    }

}
//
// $Log: $
//
