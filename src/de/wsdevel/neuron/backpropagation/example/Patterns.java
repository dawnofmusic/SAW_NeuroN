package de.wsdevel.neuron.backpropagation.example;

import de.wsdevel.neuron.backpropagation.Pattern;

/**
 * Created on 10.04.2012 for project: SAW_NeuroN
 * 
 * (c) 2012 Sebastian A. Weiﬂ - All rights reserved.
 * 
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 */
public final class Patterns {

    /**
     * {@link Pattern[]} COMMENT.
     */
    public static final Pattern[] EXAMPLE_PATTERNS = new Pattern[] {

	    new Pattern("XOR", new double[][] { new double[] { 1, 1 },
		    new double[] { 1, 0 }, new double[] { 0, 1 },
		    new double[] { 0, 0 } }, new double[] { 0, 1, 1, 0 }),

	    new Pattern("OR", new double[][] { new double[] { 1, 1 },
		    new double[] { 1, 0 }, new double[] { 0, 1 },
		    new double[] { 0, 0 } }, new double[] { 1, 1, 1, 0 }),

	    new Pattern("AND", new double[][] { new double[] { 1, 1 },
		    new double[] { 1, 0 }, new double[] { 0, 1 },
		    new double[] { 0, 0 } }, new double[] { 1, 0, 0, 0 }),

	    new Pattern("Letter 'U'", new double[][] {
		    new double[] { 0.3f, 0.4f }, new double[] { 0.7f, 0.7f },
		    new double[] { 0.3f, 0.7f }, new double[] { 0.7f, 0.4f },
		    new double[] { 0.2f, 0.2f }, new double[] { 0.5f, 0.1f },
		    new double[] { 0.8f, 0.2f }, new double[] { 0.1f, 0.5f },
		    new double[] { 0.9f, 0.5f }, new double[] { 0.1f, 0.9f },
		    new double[] { 0.5f, 0.3f }, new double[] { 0.9f, 0.9f } },
		    new double[] { 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1 }),

	    new Pattern("Cage", new double[][] { new double[] { 0, 0 },
		    new double[] { 0, 0.5f }, new double[] { 0, 1 },
		    new double[] { 0.5f, 0 }, new double[] { 0.5f, 0.5f },
		    new double[] { 0.5f, 1 }, new double[] { 1, 0 },
		    new double[] { 1, 0.5f }, new double[] { 1, 1 } },
		    new double[] { 0, 0, 0, 0, 1, 0, 0, 0, 0 }),

	    new Pattern("Cross 1", new double[][] { new double[] { 0, 0 },
		    new double[] { 0, 0.5f }, new double[] { 0, 1 },
		    new double[] { 0.5f, 0 }, new double[] { 0.5f, 0.5f },
		    new double[] { 0.5f, 1 }, new double[] { 1, 0 },
		    new double[] { 1, 0.5f }, new double[] { 1, 1 } },
		    new double[] { 0, 1, 0, 1, 0, 1, 0, 1, 0 }),

	    new Pattern("Cross 2", new double[][] { new double[] { 0, 0 },
		    new double[] { 0, 0.5f }, new double[] { 0, 1 },
		    new double[] { 0.5f, 0 }, new double[] { 0.5f, 0.5f },
		    new double[] { 0.5f, 1 }, new double[] { 1, 0 },
		    new double[] { 1, 0.5f }, new double[] { 1, 1 } },
		    new double[] { 0, 1, 0, 1, 1, 1, 0, 1, 0 }),

	    new Pattern("Cross 3", new double[][] {
		    new double[] { 0.5f, -0.25f }, new double[] { 0.25f, 0 },
		    new double[] { 0.5f, 0 }, new double[] { 0.75f, 0 },
		    new double[] { 0, 0.25f }, new double[] { 0.5f, 0.25f },
		    new double[] { 1, 0.25f }, new double[] { -0.25f, 0.5f },
		    new double[] { 0, 0.5f }, new double[] { 0.25f, 0.5f },
		    new double[] { 0.5f, 0.5f }, new double[] { 0.75f, 0.5f },
		    new double[] { 1, 0.5f }, new double[] { 1.25f, 0.5f },
		    new double[] { 0, 0.75f }, new double[] { 0.5f, 0.75f },
		    new double[] { 1, 0.75f }, new double[] { 0.25f, 1 },
		    new double[] { 0.5f, 1 }, new double[] { 0.75f, 1 },
		    new double[] { 0.5f, 1.25f } }, new double[] { 0, 0, 1, 0,
		    0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0 })

    };

}
//
// $Log: $
//
