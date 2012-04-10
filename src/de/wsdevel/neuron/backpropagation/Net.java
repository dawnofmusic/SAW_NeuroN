package de.wsdevel.neuron.backpropagation;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wsdevel.neuron.nnsimula.NNSimulaPattern;

/**
 * Created on 09.04.2012 for project: SAW_NeuroN
 * 
 * (c) 2012 Sebastian A. Weiﬂ - All rights reserved.
 * 
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 */
public class Net {

    /**
     * Created on 09.04.2012 for project: SAW_NeuroN
     * 
     * (c) 2012 Sebastian A. Weiﬂ - All rights reserved.
     * 
     * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
     * @version $Author: $ -- $Revision: $ -- $Date: $
     */
    public static class LearnResult {
	public long stepsNeeded;
	public double standardDeviation;
    }

    /**
     * {@link Neuron[][]} COMMENT.
     */
    private final Neuron[][] net;

    /**
     * {@link Random} COMMENT.
     */
    private final static Random RANDOM = new Random(System.currentTimeMillis());

    /**
     * {@link double} COMMENT.
     */
    private double learningRate;

    /**
     * {@link double} COMMENT.
     */
    private double maxLearningRate = 0.2;

    /**
     * {@link double} COMMENT.
     */
    private double minLearningRate = 0.1;

    /**
     * {@link double} COMMENT.
     */
    private double maxIterations = 20000;

    /**
     * {@link double} COMMENT.
     */
    private double gradient = 2.4;

    /**
     * {@link Log} COMMENT.
     */
    private static final Log LOG = LogFactory.getLog(Net.class);

    /**
     * {@link PropertyChangeSupport} COMMENT.
     */
    private final PropertyChangeSupport pcs;

    /**
     * COMMENT.
     * 
     * @param numberOfNeuronsInLayersRef
     */
    public Net(final int[] numberOfNeuronsInLayersRef) {
	this.net = new Neuron[numberOfNeuronsInLayersRef.length][0];
	for (int layer = 0; layer < numberOfNeuronsInLayersRef.length; layer++) {
	    this.net[layer] = new Neuron[numberOfNeuronsInLayersRef[layer]];
	    for (int index = 0; index < numberOfNeuronsInLayersRef[layer]; index++) {
		final Neuron neuron = new Neuron();
		this.net[layer][index] = neuron;
		if (layer == 0) {
		    // input layer
		    neuron.setType(Neuron.Type.Input);
		} else if ((layer + 1) == numberOfNeuronsInLayersRef.length) {
		    // output layer (saw)
		    neuron.setType(Neuron.Type.Output);
		} else {
		    neuron.setType(Neuron.Type.Hidden);
		}
		if (layer > 0) {
		    neuron.weights = new double[numberOfNeuronsInLayersRef[layer - 1]];
		}

	    }
	}
	resetWeights();
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
     * COMMENT.
     * 
     * @param pattern
     * @param outputNeuronsNumber
     *            <code>int</code>
     * @param currentResult
     * @return
     */
    private double calculateStandardDeviation(final Pattern pattern,
	    final int outputNeuronsNumber, final double[][] currentResult) {
	double totalDeviation = 0;
	for (int vectorIndex = 0; vectorIndex < pattern.targetOutputVector.length; vectorIndex++) {
	    double deviation = 0;
	    for (int neuronIndex = 0; neuronIndex < outputNeuronsNumber; neuronIndex++) {
		deviation = deviation
			+ ((pattern.targetOutputVector[vectorIndex] - currentResult[vectorIndex][neuronIndex]) * (pattern.targetOutputVector[vectorIndex] - currentResult[vectorIndex][neuronIndex]));
	    }
	    totalDeviation = totalDeviation + (deviation / outputNeuronsNumber);
	}
	final double standardDeviation = Math.sqrt(totalDeviation
		/ pattern.targetOutputVector.length);
	return standardDeviation;
    }

    /**
     * COMMENT.
     * 
     * @param pattern
     *            {@link NNSimulaPattern}
     */
    private double doLearnStep(final Pattern pattern) {
	final int outputNeuronsNumber = getNumberOfNeuronsInLayer(getNumberOfLayers() - 1);
	final double[][] currentResult = new double[pattern.inputVectors.length][outputNeuronsNumber];

	for (int vectorIndex = 0; vectorIndex < pattern.inputVectors.length; vectorIndex++) {
	    setInput(pattern.inputVectors[vectorIndex]);
	    // 1st: propagating forwards.
	    for (int layer = 1; layer < getNumberOfLayers(); layer++) {
		for (int neuronIndex = 0; neuronIndex < getNumberOfNeuronsInLayer(layer); neuronIndex++) {
		    final Neuron neuron = getNeuron(layer, neuronIndex);
		    neuron.setActivity(neuron.weights[0]);
		    for (int weightIndex = 0; weightIndex < getNumberOfNeuronsInLayer(layer - 1); weightIndex++) {
			neuron.setActivity(neuron.getActivity()
				+ (getNeuron(layer - 1, weightIndex)
					.getOutput() * neuron.weights[weightIndex]));
		    }
		    neuron.setOutput(1 / (1 + Math.exp(-getGradient()
			    * neuron.getActivity())));
		}
	    }
	    // 2nd: propagating backwards.
	    for (int layer = getNumberOfLayers() - 1; layer > -1; layer--) {
		for (int neuronIndex = 0; neuronIndex < getNumberOfNeuronsInLayer(layer); neuronIndex++) {
		    final Neuron neuron = getNeuron(layer, neuronIndex);
		    switch (neuron.getType()) {
		    case Output:
			neuron.setDeviation(getGradient()
				* (1 / (1 + Math.exp(-getGradient()
					* neuron.getActivity())))
				* (1 - (1 / (1 + Math.exp(-getGradient()
					* neuron.getActivity()))))
				* (pattern.targetOutputVector[vectorIndex] - neuron
					.getOutput()));
			// store output
			currentResult[vectorIndex][neuronIndex] = neuron
				.getOutput();
			break;
		    case Hidden:
			neuron.setDeviation(0);
			for (int i = 0; i < getNumberOfNeuronsInLayer(layer + 1); i++) {
			    final Neuron neuron2 = getNeuron(layer + 1, i);
			    neuron.setDeviation(neuron.getDeviation()
				    + (neuron2.getDeviation() * neuron2.weights[neuronIndex]));
			}
			neuron.setDeviation(neuron.getDeviation()
				* getGradient()
				* (1 / (1 + Math.exp(-getGradient()
					* neuron.getActivity())))
				* (1 - (1 / (1 + Math.exp(-getGradient()
					* neuron.getActivity())))));
			break;
		    }
		    // !! Gewichte anpassen !!
		    if (layer > 0) {
			neuron.weights[0] = neuron.weights[0]
				+ (getLearningRate() * neuron.getDeviation());
			for (int weightIndex = 0; weightIndex < getNumberOfNeuronsInLayer(layer - 1); weightIndex++) {
			    neuron.weights[weightIndex] = neuron.weights[weightIndex]
				    + (getLearningRate()
					    * neuron.getDeviation() * getNeuron(
						layer - 1, weightIndex)
					    .getOutput());
			}
		    }
		}
	    }
	}
	return calculateStandardDeviation(pattern, outputNeuronsNumber,
		currentResult);
    }

    /**
     * @return {@link double} the gradient.
     */
    public double getGradient() {
	return this.gradient;
    }

    /**
     * @return {@link double} the learningRate.
     */
    public double getLearningRate() {
	return this.learningRate;
    }

    /**
     * @return {@link double} the maxIterations.
     */
    public double getMaxIterations() {
	return this.maxIterations;
    }

    /**
     * @return {@link double} the maxLearningRate.
     */
    public double getMaxLearningRate() {
	return this.maxLearningRate;
    }

    /**
     * @return {@link double} the minLearningRate.
     */
    public double getMinLearningRate() {
	return this.minLearningRate;
    }

    /**
     * COMMENT.
     * 
     * @param layer
     *            <code>int</code>
     * @param index
     *            <code>int</code>
     * @return {@link Neuron}
     */
    public Neuron getNeuron(final int layer, final int index) {
	return this.net[layer][index];
    }

    /**
     * @return {@link int[]} the numberOfNeuronsInLayer.
     */
    public int getNumberOfLayers() {
	return this.net.length;
    }

    /**
     * @return {@link int[]} the numberOfNeuronsInLayer.
     */
    public int getNumberOfNeuronsInLayer(final int layer) {
	return this.net[layer].length;
    }

    /**
     * COMMENT.
     * 
     * @param inputvector
     *            <code>double</code>
     * @return <code>double</code>
     */
    public double getOutputFromFirstOutputNeuronForInput(
	    final double[] inputvector) {
	setInput(inputvector);
	for (int layer = 1; layer < getNumberOfLayers(); layer++) {
	    for (int neuronIndex = 0; neuronIndex < getNumberOfNeuronsInLayer(layer); neuronIndex++) {
		final Neuron neuron = getNeuron(layer, neuronIndex);
		neuron.setActivity(neuron.weights[0]);
		for (int weightIndex = 0; weightIndex < getNumberOfNeuronsInLayer(layer - 1); weightIndex++) {
		    neuron.setActivity(neuron.getActivity()
			    + (getNeuron(layer - 1, weightIndex).getOutput() * neuron.weights[weightIndex]));
		}
		neuron.setOutput(1 / (1 + Math.exp(-getGradient()
			* neuron.getActivity())));
	    }
	}
	return getNeuron(getNumberOfLayers() - 1, 0).getOutput();
    }

    /**
     * COMMENT.
     * 
     * @param pattern
     *            {@link NNSimulaPattern}
     * @return <code>double</code>
     */
    public LearnResult learn(final Pattern pattern) {
	if (Net.LOG.isDebugEnabled()) {
	    Net.LOG.info("Starting to learn...");
	}
	setLearningRate(getMaxLearningRate());
	long steps = 0;
	final double rateDiff = (getMaxLearningRate() - getMinLearningRate())
		/ getMaxIterations();
	double sd = 1;
	do {
	    sd = doLearnStep(pattern);
	    setLearningRate(getLearningRate() - rateDiff);
	    steps++;
	    if (Net.LOG.isDebugEnabled()) {
		Net.LOG.info("[step: " + steps + ", standard deviation: " + sd
			+ ", learning rate: " + getLearningRate() + ", diff: "
			+ rateDiff + "]");
	    }
	} while ((getLearningRate() > 0) && (steps < getMaxIterations()));
	if (Net.LOG.isInfoEnabled()) {
	    Net.LOG.info("finished learning. [steps: " + steps
		    + ", standard deviation: " + sd + "]");
	}
	final LearnResult lr = new LearnResult();
	lr.standardDeviation = sd;
	lr.stepsNeeded = steps;
	return lr;
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
     * Resets the weights to new random values.
     */
    public void resetWeights() {
	// start with second layer, no weights needed for input layer (saw
	// 09042012)
	for (int layer = 1; layer < this.net.length; layer++) {
	    for (int index = 0; index < this.net[layer].length; index++) {
		final Neuron neuron = getNeuron(layer, index);
		for (int weightIndex = 0; weightIndex < neuron.weights.length; weightIndex++) {
		    double weight = 0;
		    do {
			weight = 1 - (2 * Net.RANDOM.nextDouble());
		    } while (weight == 0);
		    neuron.weights[weightIndex] = weight;
		}
	    }
	}
    }

    /**
     * @param gradient
     *            {@link double} the gradient to set.
     */
    public void setGradient(final double gradient) {
	final double oldValue = this.gradient;
	this.gradient = gradient;
	this.pcs.firePropertyChange("gradient", oldValue, this.gradient);
    }

    /**
     * @param inputvector
     *            <code>double[]</code> to be set to input layer.
     */
    private void setInput(final double[] inputvector) {
	if (inputvector.length > getNumberOfNeuronsInLayer(0)) {
	    throw new IllegalArgumentException(
		    "inputvector is to big for the input layer!");
	} else if (inputvector.length < getNumberOfNeuronsInLayer(0)) {
	    if (Net.LOG.isWarnEnabled()) {
		Net.LOG.warn("inputvector smaller than input layer!");
	    }
	}
	for (int i = 0; i < inputvector.length; i++) {
	    getNeuron(0, i).setOutput(inputvector[i]);
	}
    }

    /**
     * @param learningRate
     *            {@link double} the learningRate to set.
     */
    public void setLearningRate(final double learningRate) {
	final double oldValue = this.learningRate;
	this.learningRate = learningRate;
	this.pcs.firePropertyChange("learningRate", oldValue, this.learningRate);
    }

    /**
     * @param maxIterations
     *            {@link double} the maxIterations to set.
     */
    public void setMaxIterations(final double maxIterations) {
	final double oldValue = this.maxIterations;
	this.maxIterations = maxIterations;
	this.pcs.firePropertyChange("maxIterations", oldValue,
		this.learningRate);
    }

    /**
     * @param maxLearningRate
     *            {@link double} the maxLearningRate to set.
     */
    public void setMaxLearningRate(final double maxLearningRate) {
	final double oldValue = this.maxLearningRate;
	this.maxLearningRate = maxLearningRate;
	this.pcs.firePropertyChange("maxLearningRate", oldValue,
		this.maxLearningRate);
    }

    /**
     * @param minLearningRate
     *            {@link double} the minLearningRate to set.
     */
    public void setMinLearningRate(final double minLearningRate) {
	final double oldValue = this.minLearningRate;
	this.minLearningRate = minLearningRate;
	this.pcs.firePropertyChange("minLearningRate", oldValue,
		this.minLearningRate);
    }

    /**
     * COMMENT.
     * 
     * @param inputNeurons
     * @param hiddenLayers
     * @param neuronsInHiddenLayer
     * @param outputNeurons
     * @return
     */
    public static Net createNet(int inputNeurons, int hiddenLayers,
	    int neuronsInHiddenLayer, int outputNeurons) {
	final int[] numberOfNeuronsInLayer = new int[hiddenLayers + 2];
	numberOfNeuronsInLayer[0] = inputNeurons;
	for (int x = 0; x < hiddenLayers; x++) {
	    numberOfNeuronsInLayer[x + 1] = neuronsInHiddenLayer;
	}
	numberOfNeuronsInLayer[numberOfNeuronsInLayer.length - 1] = outputNeurons;
	Net net = new Net(numberOfNeuronsInLayer);
	return net;
    }

}
//
// $Log: $
//
