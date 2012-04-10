package de.wsdevel.neuron.backpropagation.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import de.wsdevel.neuron.backpropagation.Net;
import de.wsdevel.neuron.backpropagation.Pattern;

/**
 * Created on 10.04.2012 for project: SAW_NeuroN
 * 
 * (c) 2012 Sebastian A. Weiﬂ - All rights reserved.
 * 
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 */
public class PatternPanel extends JPanel {

    /**
     * {@link long} COMMENT.
     */
    private static final long serialVersionUID = 2459719197717122970L;

    /**
     * {@link Pattern} COMMENT.
     */
    private Pattern pattern;

    /**
     * {@link BufferedImage} COMMENT.
     */
    private Image result;

    /**
     * Default constructor.
     */
    public PatternPanel() {
	super(null);
	setSize(300, 300);
	setBackground(Color.black);
	setForeground(Color.white);
	addComponentListener(new ComponentAdapter() {
	    @Override
	    public void componentResized(final ComponentEvent e) {
		PatternPanel.this.result = null;
		repaint();
	    }
	});
	System.instance.addPropertyChangeListener("currentLearnResult",
		new PropertyChangeListener() {
		    @Override
		    public void propertyChange(final PropertyChangeEvent evt) {
			showResult(System.instance.getNet(), System.instance
				.getStartLearningAction().isStopped() ? 1 : 8);
		    }
		});
	System.instance.addPropertyChangeListener("patternToBeTrained",
		new PropertyChangeListener() {
		    @Override
		    public void propertyChange(final PropertyChangeEvent evt) {
			setPattern(System.instance.getPatternToBeTrained());
		    }
		});
	setPattern(System.instance.getPatternToBeTrained());
    }

    /**
     * @return {@link Pattern} the pattern.
     */
    public Pattern getPattern() {
	return this.pattern;
    }

    /**
     * COMMENT.
     * 
     * @param g
     * @param x
     * @param y
     */
    private void paintCircle(final Graphics g, final double x, final double y) {
	final int xcor = (int) Math.round((getWidth() * 0.25)
		+ ((getWidth() * 0.5) * x));
	final int ycor = (int) Math.round((getHeight() * 0.75)
		- ((getHeight() * 0.5) * y));
	g.drawOval(xcor - 3, ycor - 3, 3 * 2, 3 * 2);
    }

    /**
     * @param g
     *            {@link Graphics}
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(final Graphics g) {
	g.setColor(getBackground());
	g.fillRect(0, 0, getWidth(), getHeight());
	if (this.result != null) {
	    g.drawImage(this.result, 0, 0, this);
	}
	g.setColor(getForeground());
	g.setXORMode(Color.black);
	for (int vectorIndex = 0; vectorIndex < getPattern().inputVectors.length; vectorIndex++) {
	    if (getPattern().targetOutputVector[vectorIndex] < 0.5) {
		paintCircle(g, getPattern().inputVectors[vectorIndex][0],
			getPattern().inputVectors[vectorIndex][1]);
	    } else {
		paintCross(g, getPattern().inputVectors[vectorIndex][0],
			getPattern().inputVectors[vectorIndex][1]);
	    }
	}
	g.setPaintMode();
	g.setColor(Color.black);
	g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    /**
     * COMMENT.
     * 
     * @param g
     * @param x
     * @param y
     */
    private void paintCross(final Graphics g, final double x, final double y) {
	final int xcor = (int) Math.round((getWidth() * 0.25)
		+ ((getWidth() * 0.5) * x));
	final int ycor = (int) Math.round((getHeight() * 0.75)
		- ((getHeight() * 0.5) * y));
	g.drawLine(xcor - 3, ycor, xcor + 3, ycor);
	g.drawLine(xcor, ycor - 3, xcor, ycor + 3);
    }

    /**
     * @param pattern
     *            {@link Pattern} the pattern to set.
     */
    public void setPattern(final Pattern pattern) {
	this.pattern = pattern;
	this.result = null;
	repaint();
    }

    /**
     * {@link Object} COMMENT.
     */
    private Object lock = new Object();

    /**
     * COMMENT.
     * 
     * @param net
     *            Net
     * @param dither
     */
    private void showResult(final Net net, final int dither) {
	if (net == null) {
	    return;
	}
	synchronized (lock) {
	    if (this.result != null) {
		this.result = null;
	    }
	    this.result = createImage(getWidth(), getHeight());
	    final Graphics g = this.result.getGraphics();

	    final double deltaX = 2.0d / getWidth();
	    final double deltaY = 2.0d / getHeight();
	    final double halfWidth = getWidth() / 2.0d;
	    final double halfHeight = getHeight() / 2.0d;
	    double xValue = -0.50;
	    do {
		double yValue = -0.50;
		do {
		    final double output = net
			    .getOutputFromFirstOutputNeuronForInput(new double[] {
				    xValue, yValue });
		    final int xCoor = (int) Math.round((xValue + 0.5)
			    * halfWidth);
		    final int yCoor = (int) (getHeight() - 1 - Math
			    .round((yValue + 0.5) * halfHeight));

		    final int round = (int) Math.round(255 * output);
		    g.setColor(new Color(round, round, round));
		    g.fillRect(xCoor, yCoor, dither, dither);

		    yValue += (dither * deltaY);
		} while (yValue < 1.5);
		xValue += (dither * deltaX);
	    } while (xValue < 1.5);
	    repaint();
	}
    }

}
//
// $Log: $
//
