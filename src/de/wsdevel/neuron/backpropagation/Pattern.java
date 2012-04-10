package de.wsdevel.neuron.backpropagation;

/**
 * Created on 09.04.2012 for project: SAW_NeuroN
 * 
 * (c) 2012 Sebastian A. Weiﬂ - All rights reserved.
 * 
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 */
public class Pattern {

    /**
     * {@link String} COMMENT.
     */
    private String name;

    /**
     * @return {@link String} the name.
     */
    public String getName() {
	return this.name;
    }

    /**
     * @param name
     *            {@link String} the name to set.
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * {@link double[][]} COMMENT.
     */
    public double[][] inputVectors;

    /**
     * {@link double[]} COMMENT.
     */
    public double[] targetOutputVector;

    /**
     * COMMENT.
     * 
     * @param inputVectorsRef
     *            <code>double[][]</code>
     * @param targetOutputVectorRef
     *            <code>double[]</code>
     */
    public Pattern(final String nameVal, final double[][] inputVectorsRef,
	    final double[] targetOutputVectorRef) {
	if (inputVectorsRef.length != targetOutputVectorRef.length) {
	    throw new IllegalArgumentException(
		    "number of input vectors has to be the same as the size of the target output vector!");
	}
	this.inputVectors = inputVectorsRef;
	this.targetOutputVector = targetOutputVectorRef;
	setName(nameVal);
    }

}
//
// $Log: $
//
