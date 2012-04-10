package de.wsdevel.neuron.backpropagation.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Created on 10.04.2012 for project: SAW_NeuroN
 * 
 * (c) 2012 Sebastian A. Weiﬂ - All rights reserved.
 * 
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 */
public class StopLearningAction extends AbstractAction {

    /**
     * {@link long} COMMENT.
     */
    private static final long serialVersionUID = -2311973124802920967L;

    /**
     * COMMENT.
     */
    public StopLearningAction() {
	super("Stop Learning");
	setEnabled(false);
    }

    /**
     * @param e
     *            {@link ActionEvent}
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
	setEnabled(false);
	de.wsdevel.neuron.backpropagation.ui.System.instance
		.getStartLearningAction().setStopped(true);
    }

}
//
// $Log: $
//
