package de.wsdevel.neuron.backpropagation;

/**
 * Created on 09.04.2012 for project: SAW_NeuroN
 * 
 * (c) 2012 Sebastian A. Weiﬂ - All rights reserved.
 * 
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 */
public class Neuron {

    /**
     * Created on 09.04.2012 for project: SAW_NeuroN
     * 
     * (c) 2012 Sebastian A. Weiﬂ - All rights reserved.
     * 
     * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
     * @version $Author: $ -- $Revision: $ -- $Date: $
     */
    public static enum Type {
	/**
	 * {@link Type} marks neuron as input.
	 */
	Input,

	/**
	 * {@link Type} marks neuron as hidden.
	 */
	Hidden,

	/**
	 * {@link Type} marks neuron as output.
	 */
	Output;
    }

    /**
     * {@link Type} COMMENT.
     */
    private Type type = Type.Hidden;

    /**
     * {@link double} COMMENT.
     */
    private double activity;

    /**
     * {@link double} COMMENT.
     */
    private double output;

    /**
     * {@link double} COMMENT.
     */
    private double deviation;

    /**
     * {@link double[]} COMMENT.
     */
    public double[] weights;

    /**
     * @return {@link double} the activity.
     */
    public double getActivity() {
	return this.activity;
    }

    /**
     * @return {@link double} the deviation.
     */
    public double getDeviation() {
	return this.deviation;
    }

    /**
     * @return {@link double} the output.
     */
    public double getOutput() {
	return this.output;
    }

    /**
     * @return {@link Type} the type.
     */
    public Type getType() {
	return this.type;
    }

    /**
     * @param activity
     *            {@link double} the activity to set.
     */
    public void setActivity(double activity) {
	this.activity = activity;
    }

    /**
     * @param deviation
     *            {@link double} the deviation to set.
     */
    public void setDeviation(double deviation) {
	this.deviation = deviation;
    }

    /**
     * @param output
     *            {@link double} the output to set.
     */
    public void setOutput(double output) {
	this.output = output;
    }

    /**
     * @param type
     *            {@link Type} the type to set.
     */
    public void setType(Type type) {
	this.type = type;
    }
}
//
// $Log: $
//
