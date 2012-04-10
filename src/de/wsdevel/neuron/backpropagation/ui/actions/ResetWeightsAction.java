package de.wsdevel.neuron.backpropagation.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import de.wsdevel.neuron.backpropagation.ui.System;

/**
 * Created on 10.04.2012 for project: SAW_NeuroN
 * 
 * (c) 2012 Sebastian A. Weiﬂ - All rights reserved.
 * 
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 */
public class ResetWeightsAction extends AbstractAction {

    /**
     * {@link long} COMMENT.
     */
    private static final long serialVersionUID = 5232528884327746133L;

    /**
     * COMMENT.
     */
    public ResetWeightsAction() {
	super("Reset Net Weights");
	setEnabled(false);
    }

    /**
     * @param e
     *            {@link ActionEvent}
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
	System.instance.getNet().resetWeights();
	setEnabled(false);
    }

}
//
// $Log: $
//
