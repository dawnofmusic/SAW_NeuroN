package de.wsdevel.neuron.backpropagation.ui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Timer;

import de.wsdevel.neuron.backpropagation.Net;
import de.wsdevel.neuron.backpropagation.Net.LearnResult;
import de.wsdevel.neuron.backpropagation.Pattern;
import de.wsdevel.neuron.backpropagation.ui.actions.ResetWeightsAction;
import de.wsdevel.neuron.backpropagation.ui.actions.StartLearningAction;
import de.wsdevel.neuron.backpropagation.ui.actions.StopLearningAction;

/**
 * Created on 10.04.2012 for project: SAW_NeuroN
 * 
 * (c) 2012 Sebastian A. Weiﬂ - All rights reserved.
 * 
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 */
public final class System {

    /**
     * {@link System} COMMENT.
     */
    public static final System instance = new System();

    /**
     * {@link PropertyChangeSupport} COMMENT.
     */
    private final PropertyChangeSupport pcs;

    /**
     * {@link LearnResult} COMMENT.
     */
    private Net.LearnResult currentLearnResult;

    /**
     * {@link Net} COMMENT.
     */
    private Net net;

    /**
     * {@link Pattern} COMMENT.
     */
    private Pattern patternToBeTrained;

    /**
     * {@link int} COMMENT.
     */
    private int dither = 8;

    /**
     * {@link StartLearningAction} COMMENT.
     */
    private final StartLearningAction startLearningAction = new StartLearningAction();

    /**
     * {@link StopLearningAction} COMMENT.
     */
    private final StopLearningAction stopLearningAction = new StopLearningAction();

    /**
     * {@link ResetWeightsAction} COMMENT.
     */
    private final ResetWeightsAction resetWeightsAction = new ResetWeightsAction();

    /**
     * {@link Timer} COMMENT.
     */
    private Timer calculationThread = new Timer("Calculation-Thread");

    /**
     * Default constructor.
     */
    public System() {
	this.pcs = new PropertyChangeSupport(this);
    }

    /**
     * @param listener
     *            {@link PropertyChangeListener}
     * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
     */
    public void addPropertyChangeListener(final PropertyChangeListener listener) {
	this.pcs.addPropertyChangeListener(listener);
    }

    /**
     * @param propertyName
     *            {@link String}
     * @param listener
     *            {@link PropertyChangeListener}
     * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.lang.String,
     *      java.beans.PropertyChangeListener)
     */
    public void addPropertyChangeListener(final String propertyName,
	    final PropertyChangeListener listener) {
	this.pcs.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * @return {@link Timer} the calculationThread.
     */
    public Timer getCalculationThread() {
	return this.calculationThread;
    }

    /**
     * @return {@link Net.LearnResult} the currentLearnResult.
     */
    public Net.LearnResult getCurrentLearnResult() {
	return this.currentLearnResult;
    }

    /**
     * @return {@link int} the dither.
     */
    public int getDither() {
	return this.dither;
    }

    /**
     * @return {@link Net} the net.
     */
    public Net getNet() {
	return this.net;
    }

    /**
     * @return {@link Pattern} the patternToBeTrained.
     */
    public Pattern getPatternToBeTrained() {
	return this.patternToBeTrained;
    }

    /**
     * @return {@link ResetWeightsAction} the resetWeightsAction.
     */
    public ResetWeightsAction getResetWeightsAction() {
	return this.resetWeightsAction;
    }

    /**
     * @return {@link StartLearningAction} the startLearningAction.
     */
    public StartLearningAction getStartLearningAction() {
	return this.startLearningAction;
    }

    /**
     * @return {@link StopLearningAction} the stopLearningAction.
     */
    public StopLearningAction getStopLearningAction() {
	return this.stopLearningAction;
    }

    /**
     * @param listener
     *            {@link PropertyChangeListener}
     * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
     */
    public void removePropertyChangeListener(
	    final PropertyChangeListener listener) {
	this.pcs.removePropertyChangeListener(listener);
    }

    /**
     * @param propertyName
     *            {@link String}
     * @param listener
     *            {@link PropertyChangeListener}
     * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.lang.String,
     *      java.beans.PropertyChangeListener)
     */
    public void removePropertyChangeListener(final String propertyName,
	    final PropertyChangeListener listener) {
	this.pcs.removePropertyChangeListener(propertyName, listener);
    }

    /**
     * @param calculationThread
     *            {@link Timer} the calculationThread to set.
     */
    public void setCalculationThread(final Timer calculationThread) {
	final Timer oldValue = this.calculationThread;
	this.calculationThread = calculationThread;
	this.pcs.firePropertyChange("calculationThread", oldValue,
		this.calculationThread);
    }

    /**
     * @param currentLearnResult
     *            {@link Net.LearnResult} the currentLearnResult to set.
     */
    public void setCurrentLearnResult(final Net.LearnResult currentLearnResult) {
	final LearnResult oldValue = this.currentLearnResult;
	this.currentLearnResult = currentLearnResult;
	this.pcs.firePropertyChange("currentLearnResult", oldValue,
		this.currentLearnResult);
    }

    /**
     * @param dither
     *            {@link int} the dither to set.
     */
    public void setDither(final int dither) {
	final int oldValue = this.dither;
	this.dither = dither;
	this.pcs.firePropertyChange("dither", oldValue, this.dither);
    }

    /**
     * @param net
     *            {@link Net} the net to set.
     */
    public void setNet(final Net net) {
	final Net oldValue = this.net;
	this.net = net;
	this.pcs.firePropertyChange("net", oldValue, this.net);
    }

    /**
     * @param patternToBeTrained
     *            {@link Pattern} the patternToBeTrained to set.
     */
    public void setPatternToBeTrained(final Pattern patternToBeTrained) {
	final Pattern oldValue = this.patternToBeTrained;
	this.patternToBeTrained = patternToBeTrained;
	this.pcs.firePropertyChange("patternToBeTrained", oldValue,
		this.patternToBeTrained);
    }

}
//
// $Log: $
//
