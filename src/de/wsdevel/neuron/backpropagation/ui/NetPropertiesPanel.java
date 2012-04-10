package de.wsdevel.neuron.backpropagation.ui;

import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.wsdevel.neuron.backpropagation.Net;
import de.wsdevel.tools.awt.GBC;

/**
 * Created on 09.04.2012 for project: SAW_NeuroN
 * 
 * (c) 2012 Sebastian A. Weiﬂ - All rights reserved.
 * 
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 */
public class NetPropertiesPanel extends JPanel {

    /**
     * {@link long} COMMENT.
     */
    private static final long serialVersionUID = -1318636492543296444L;

    /**
     * {@link Net} COMMENT.
     */
    private Net model;

    /**
     * {@link PropertyChangeListener} COMMENT.
     */
    private PropertyChangeListener pcl;

    /**
     * {@link JSpinner} COMMENT.
     */
    private final JSpinner maxIterationsSpinner;

    /**
     * {@link JSpinner} COMMENT.
     */
    private final JSpinner maxLearnRateSpinner;

    /**
     * {@link JSpinner} COMMENT.
     */
    private final JSpinner minLearnRateSpinner;

    /**
     * {@link JSpinner} COMMENT.
     */
    private final JSpinner gradientSpinner;

    /**
     * Default constructor.
     */
    public NetPropertiesPanel() {
	super(new GridBagLayout());
	setBorder(BorderFactory.createTitledBorder("Net Properties"));
	add(new JLabel("Max.Iterations"), new GBC().pos(0, 0).anchorEast());
	this.maxIterationsSpinner = new JSpinner();
	this.maxIterationsSpinner.addChangeListener(new ChangeListener() {
	    @Override
	    public void stateChanged(ChangeEvent e) {
		SpinnerModel model2 = maxIterationsSpinner.getModel();
		if (model2 instanceof SpinnerNumberModel) {
		    getModel().setMaxIterations(
			    ((SpinnerNumberModel) model2).getNumber()
				    .intValue());
		}
	    }
	});
	add(this.maxIterationsSpinner, new GBC().pos(1, 0).fillHorizontal(1));

	add(new JLabel("Max. learn rate"), new GBC().pos(0, 1).anchorEast());
	this.maxLearnRateSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1,
		0.01));
	add(this.maxLearnRateSpinner, new GBC().pos(1, 1).fillHorizontal(1));

	add(new JLabel("Min. learn rate"), new GBC().pos(0, 2).anchorEast());
	this.minLearnRateSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1,
		0.01));
	add(this.minLearnRateSpinner, new GBC().pos(1, 2).fillHorizontal(1));

	add(new JLabel("Gradient"), new GBC().pos(0, 3).anchorEast());
	this.gradientSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10,
		0.1));
	add(this.gradientSpinner, new GBC().pos(1, 3).fillHorizontal(1));
    }

    /**
     * COMMENT.
     * 
     * @param modelRef
     */
    public NetPropertiesPanel(final Net modelRef) {
	this();
	setModel(modelRef);
    }

    /**
     * @return {@link Net} the model.
     */
    public Net getModel() {
	return this.model;
    }

    /**
     * @param model
     *            {@link Net} the model to set.
     */
    public void setModel(final Net model) {
	if (this.model != null) {
	    this.model.removePropertyChangeListener(this.pcl);
	}
	this.model = model;
	if (this.model != null) {
	    if (this.pcl == null) {
		this.pcl = new PropertyChangeListener() {
		    @Override
		    public void propertyChange(final PropertyChangeEvent evt) {
			update();
		    }
		};
	    }
	    this.model.addPropertyChangeListener(this.pcl);
	    update();
	}
    }

    /**
     * COMMENT.
     */
    private void update() {
	this.gradientSpinner.setValue(this.model.getGradient());
	this.maxIterationsSpinner.setValue(this.model.getMaxIterations());
	this.maxLearnRateSpinner.setValue(this.model.getMaxLearningRate());
	this.minLearnRateSpinner.setValue(this.model.getMinLearningRate());
    }

}
//
// $Log: $
//
