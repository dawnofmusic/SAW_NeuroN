package de.wsdevel.neuron.nnsimula;

import de.wsdevel.neuron.backpropagation.Pattern;

/**
 * Created on 10.04.2012 for project: SAW_NeuroN
 *
 * (c) 2012 Sebastian A. Weiﬂ - All rights reserved.
 *
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 */
public class NNSimulaPattern extends Pattern {

    float MAX_LERNRATE;
    float MIN_LERNRATE;
    float gradient;
    float MAX_LERNSCHRITTE;
    int VERB_NEURONEN;
    int MAX_NEURONEN;

    /**
     * COMMENT.
     * 
     * @param dATA_IN
     * @param dATA_OUT
     * @param nAME
     * @param mAX_LERNRATE
     * @param mIN_LERNRATE
     * @param sTEIGUNG
     * @param mAX_LERNSCHRITTE
     * @param vERB_NEURONEN
     * @param mAX_NEURONEN
     */
    public NNSimulaPattern(double[][] dATA_IN, double[] dATA_OUT, String nAME,
	    float mAX_LERNRATE, float mIN_LERNRATE, float sTEIGUNG,
	    float mAX_LERNSCHRITTE, int vERB_NEURONEN, int mAX_NEURONEN) {
	super(nAME, dATA_IN, dATA_OUT);
	this.inputVectors = dATA_IN;
	this.targetOutputVector = dATA_OUT;
	this.MAX_LERNRATE = mAX_LERNRATE;
	this.MIN_LERNRATE = mIN_LERNRATE;
	this.gradient = sTEIGUNG;
	this.MAX_LERNSCHRITTE = mAX_LERNSCHRITTE;
	this.VERB_NEURONEN = vERB_NEURONEN;
	this.MAX_NEURONEN = mAX_NEURONEN;
    }

}
//
// $Log: $
//
