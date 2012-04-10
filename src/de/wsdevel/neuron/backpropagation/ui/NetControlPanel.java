package de.wsdevel.neuron.backpropagation.ui;

import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.wsdevel.neuron.backpropagation.Net;
import de.wsdevel.neuron.backpropagation.Pattern;
import de.wsdevel.neuron.backpropagation.example.Patterns;
import de.wsdevel.tools.awt.GBC;
import de.wsdevel.tools.awt.SwingThreadAdapter;

/**
 * Created on 10.04.2012 for project: SAW_NeuroN
 * 
 * (c) 2012 Sebastian A. Weiﬂ - All rights reserved.
 * 
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 */
public class NetControlPanel extends JPanel {

    /**
     * {@link long} COMMENT.
     */
    private static final long serialVersionUID = 6046611484152852899L;

    /**
     * Constructor.
     * 
     * @param netToBeControlled
     *            {@link Net}
     */
    public NetControlPanel(final Net netToBeControlled) {
	super(new GridBagLayout());
	setBorder(BorderFactory.createTitledBorder("Control"));

	add(new JLabel("Pattern:"), new GBC().pos(0, 0).anchorEast());
	JComboBox patternComboBox = new JComboBox(Patterns.EXAMPLE_PATTERNS);
	patternComboBox.setRenderer(new DefaultListCellRenderer() {
	    public java.awt.Component getListCellRendererComponent(
		    javax.swing.JList list, Object value, int index,
		    boolean isSelected, boolean cellHasFocus) {
		JLabel c = (JLabel) super.getListCellRendererComponent(list,
			value, index, isSelected, cellHasFocus);
		if (value != null && value instanceof Pattern) {
		    c.setText(((Pattern) value).getName());
		} else {
		    c.setText("");
		}
		return c;
	    };
	});
	patternComboBox.addItemListener(new ItemListener() {
	    @Override
	    public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
		    System.instance.setPatternToBeTrained((Pattern) e.getItem());
		}
	    }
	});

	add(patternComboBox, new GBC().pos(1, 0).fillHorizontal(1));

	add(new JLabel("Steps needed:"), new GBC().pos(0, 1).anchorEast());
	final JTextField stepsTF = new JTextField();
	stepsTF.setEditable(false);
	add(stepsTF, new GBC().pos(1, 1).fillHorizontal(1));
	add(new JLabel("Standard Deviation:"), new GBC().pos(0, 2).anchorEast());
	final JTextField sdTF = new JTextField();
	sdTF.setEditable(false);
	add(sdTF, new GBC().pos(1, 2).fillHorizontal(1));
	JPanel buttonPanel = new JPanel();
	add(buttonPanel, new GBC().pos(0, 3).gridWidthRemainder()
		.fillBoth(1, 1));
	buttonPanel.add(new JButton(System.instance.getStartLearningAction()));
	buttonPanel.add(new JButton(System.instance.getStopLearningAction()));
	buttonPanel.add(new JButton(System.instance.getResetWeightsAction()));
	System.instance.addPropertyChangeListener("currentLearnResult",
		new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent evt) {
			SwingThreadAdapter.runInSwingThread(new Runnable() {
			    @Override
			    public void run() {
				stepsTF.setText(Long.toString(System.instance
					.getCurrentLearnResult().stepsNeeded));
				sdTF.setText(Double.toString(System.instance
					.getCurrentLearnResult().standardDeviation));
			    }
			});
		    }
		});

    }

}
//
// $Log: $
//
