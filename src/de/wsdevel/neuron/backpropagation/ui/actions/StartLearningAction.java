package de.wsdevel.neuron.backpropagation.ui.actions;

import java.awt.event.ActionEvent;
import java.util.TimerTask;

import javax.swing.AbstractAction;

import de.wsdevel.neuron.backpropagation.Net;
import de.wsdevel.neuron.backpropagation.Net.LearnResult;
import de.wsdevel.neuron.backpropagation.ui.System;

/**
 * Created on 10.04.2012 for project: SAW_NeuroN
 * 
 * (c) 2012 Sebastian A. Weiﬂ - All rights reserved.
 * 
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 */
public class StartLearningAction extends AbstractAction {

    /**
     * {@link long} COMMENT.
     */
    private static final long serialVersionUID = -8720819563288589871L;

    /**
     * COMMENT.
     */
    public StartLearningAction() {
	super("(Re)Start Learning");
    }

    /**
     * {@link boolean} COMMENT.
     */
    private boolean stopped = false;

    /**
     * @param e
     *            {@link ActionEvent}
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
	setEnabled(false);
	System.instance.getStopLearningAction().setEnabled(true);
	System.instance.getCalculationThread().schedule(new TimerTask() {
	    @Override
	    public void run() {
		setStopped(false);
		System.instance.getResetWeightsAction().setEnabled(false);
		Net net = System.instance.getNet();
		while (!isStopped()) {
		    LearnResult learn = net.learn(System.instance
			    .getPatternToBeTrained());
		    System.instance.setCurrentLearnResult(learn);
		}
		setEnabled(true);
		System.instance.getResetWeightsAction().setEnabled(true);
	    }
	}, 0);
    }

    /**
     * @return {@link boolean} the stopped.
     */
    public boolean isStopped() {
	return this.stopped;
    }

    /**
     * @param stopped
     *            {@link boolean} the stopped to set.
     */
    public void setStopped(final boolean stopped) {
	this.stopped = stopped;
    }

}
//
// $Log: $
//
