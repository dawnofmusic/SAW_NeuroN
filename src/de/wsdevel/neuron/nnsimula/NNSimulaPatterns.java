package de.wsdevel.neuron.nnsimula;

/**
 * Created on 10.04.2012 for project: SAW_NeuroN
 *
 * (c) 2012 Sebastian A. Weiß - All rights reserved.
 *
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 */
public final class NNSimulaPatterns {

    static final NNSimulaPattern[] MUSTER_REC = new NNSimulaPattern[] {

	    // !! XOR Daten !!
	    // MUSTER_ANZ : 4;
	    // DATA_IN : ((1,1),(1,0),(0,1),(0,0)
	    // ,(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),
	    // (0,0),(0,0),(0,0),(0,0),(0,0));
	    // DATA_OUT : ( 0 , 1 , 1 , 0 ,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
	    // NAME : 'Xor (im Enheitsquadrat)';
	    // MAX_LERNRATE: 0.7;
	    // MIN_LERNRATE: 0.7;
	    // STEIGUNG: 1.25;
	    // MAX_LERNSCHRITTE:5100;
	    // VERB_NEURONEN:3
	    // ),(
	    new NNSimulaPattern(new double[][] { new double[] { 1, 1 },
		    new double[] { 1, 0 }, new double[] { 0, 1 },
		    new double[] { 0, 0 } }, new double[] { 0, 1, 1, 0 },
		    "Xor (im Enheitsquadrat)", 0.7f, 0.7f, 1.25f, 5100, 3, 0),

	    // !! OR Daten !!
	    // MUSTER_ANZ : 4;
	    // DATA_IN : ((1,1),(1,0),(0,1),(0,0)
	    // ,(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),
	    // (0,0),(0,0),(0,0),(0,0),(0,0));
	    // DATA_OUT : ( 1 , 1 , 1 , 0 ,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
	    // NAME : 'Or " ';
	    // MAX_LERNRATE: 0.6;
	    // MIN_LERNRATE: 0.6;
	    // STEIGUNG: 0.8;
	    // MAX_LERNSCHRITTE:5100;
	    // VERB_NEURONEN:1
	    // ),(
	    new NNSimulaPattern(new double[][] { new double[] { 1, 1 },
		    new double[] { 1, 0 }, new double[] { 0, 1 },
		    new double[] { 0, 0 } }, new double[] { 1, 1, 1, 0 }, "Or",
		    0.6f, 0.6f, 0.8f, 5100, 3, 0),

	    // !! AND Daten !!
	    // MUSTER_ANZ : 4;
	    // DATA_IN : ((1,1),(1,0),(0,1),(0,0)
	    // ,(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),
	    // (0,0),(0,0),(0,0),(0,0),(0,0));
	    // DATA_OUT : ( 1 , 0 , 0 , 0 ,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
	    // NAME : 'And " ';
	    // MAX_LERNRATE: 0.6;
	    // MIN_LERNRATE: 0.6;
	    // STEIGUNG: 0.8;
	    // MAX_LERNSCHRITTE:5100;
	    // VERB_NEURONEN:1
	    // ),(
	    new NNSimulaPattern(new double[][] { new double[] { 1, 1 },
		    new double[] { 1, 0 }, new double[] { 0, 1 },
		    new double[] { 0, 0 } }, new double[] { 1, 0, 0, 0 },
		    "And", 0.6f, 0.6f, 0.8f, 5100, 3, 0),

	    // !! U-Rohr Daten !!
	    // MUSTER_ANZ : 12;
	    // DATA_IN : ( (0.3,0.4),(0.7,0.7),(0.3,0.7),(0.7,0.4),(0.2,0.2),
	    // (0.5,0.1),(0.8,0.2),(0.1,0.5),(0.9,0.5),(0.1,0.9),(0.5,0.3),(0.9,0.9)
	    // ,(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0));
	    // DATA_OUT : ( 0 , 0 , 0 , 0 , 1 ,
	    // 1 , 1 , 1 , 1 , 1 , 0 , 1
	    // ,0,0,0,0,0,0,0,0,0);
	    // NAME : 'Buchstabe ''U'' ';
	    // MAX_LERNRATE: 0.2;
	    // MIN_LERNRATE: 0.1;
	    // STEIGUNG: 2.5;
	    // MAX_LERNSCHRITTE:6100;
	    // VERB_NEURONEN:4
	    // ),(
	    new NNSimulaPattern(new double[][] { new double[] { 0.3f, 0.4f },
		    new double[] { 0.7f, 0.7f }, new double[] { 0.3f, 0.7f },
		    new double[] { 0.7f, 0.4f }, new double[] { 0.2f, 0.2f },
		    new double[] { 0.5f, 0.1f }, new double[] { 0.8f, 0.2f },
		    new double[] { 0.1f, 0.5f }, new double[] { 0.9f, 0.5f },
		    new double[] { 0.1f, 0.9f }, new double[] { 0.5f, 0.3f },
		    new double[] { 0.9f, 0.9f } }, new double[] { 0, 0, 0, 0,
		    1, 1, 1, 1, 1, 1, 0, 1 }, "Buchstabe 'U'", 0.2f, 0.1f,
		    2.5f, 6100, 8, 0),

	    // !! Käfig Daten !!
	    // MUSTER_ANZ : 9;
	    // DATA_IN :
	    // (
	    // (0,0),(0,0.5),(0,1),(0.5,0),(0.5,0.5),(0.5,1),(1,0),(1,0.5),(1,1),
	    // (0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0));
	    // DATA_OUT :
	    // ( 0 , 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 ,
	    // 0,0,0,0,0,0,0,0,0,0,0,0);
	    // NAME : 'Käfig für das +';
	    // MAX_LERNRATE: 0.1;
	    // MIN_LERNRATE: 0.1;
	    // STEIGUNG: 2.5;
	    // MAX_LERNSCHRITTE:6100;
	    // VERB_NEURONEN:4
	    // ),(
	    new NNSimulaPattern(new double[][] { new double[] { 0, 0 },
		    new double[] { 0, 0.5f }, new double[] { 0, 1 },
		    new double[] { 0.5f, 0 }, new double[] { 0.5f, 0.5f },
		    new double[] { 0.5f, 1 }, new double[] { 1, 0 },
		    new double[] { 1, 0.5f }, new double[] { 1, 1 } },
		    new double[] { 0, 0, 0, 0, 1, 0, 0, 0, 0 },
		    "Käfig für das +", 0.1f, 0.1f, 2.5f, 6100, 4, 0),

	    // !! Kreuz Daten !!
	    // MUSTER_ANZ : 9;
	    // DATA_IN :
	    // (
	    // (0,0),(0,0.5),(0,1),(0.5,0),(0.5,0.5),(0.5,1),(1,0),(1,0.5),(1,1),
	    // (0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0));
	    // DATA_OUT :
	    // ( 0 , 1 , 0 , 1 , 0 , 1 , 0 , 1 , 0 ,
	    // 0,0,0,0,0,0,0,0,0,0,0,0);
	    // NAME : 'Kreuz aus Einsen';
	    // MAX_LERNRATE: 0.2;
	    // MIN_LERNRATE: 0.1;
	    // STEIGUNG: 2.4;
	    // MAX_LERNSCHRITTE: 20000;
	    // VERB_NEURONEN:8
	    // ),(
	    new NNSimulaPattern(new double[][] { new double[] { 0, 0 },
		    new double[] { 0, 0.5f }, new double[] { 0, 1 },
		    new double[] { 0.5f, 0 }, new double[] { 0.5f, 0.5f },
		    new double[] { 0.5f, 1 }, new double[] { 1, 0 },
		    new double[] { 1, 0.5f }, new double[] { 1, 1 } },
		    new double[] { 0, 1, 0, 1, 0, 1, 0, 1, 0 },
		    "Kreuz aus Einsen", 0.2f, 0.1f, 2.4f, 20000, 8, 0),

	    // {!! Kreuz II Daten !!}
	    // MUSTER_ANZ : 9;
	    // DATA_IN :
	    // (
	    // (0,0),(0,0.5),(0,1),(0.5,0),(0.5,0.5),(0.5,1),(1,0),(1,0.5),(1,1),
	    // (0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0),(0,0));
	    // DATA_OUT :
	    // ( 0 , 1 , 0 , 1 , 1 , 1 , 0 , 1 , 0 ,
	    // 0,0,0,0,0,0,0,0,0,0,0,0);
	    // NAME : 'Kreuz II aus Einsen';
	    // MAX_LERNRATE: 0.2;
	    // MIN_LERNRATE: 0.1;
	    // STEIGUNG: 2.4;
	    // MAX_LERNSCHRITTE: 5100;
	    // VERB_NEURONEN:8
	    // ),(
	    new NNSimulaPattern(new double[][] { new double[] { 0, 0 },
		    new double[] { 0, 0.5f }, new double[] { 0, 1 },
		    new double[] { 0.5f, 0 }, new double[] { 0.5f, 0.5f },
		    new double[] { 0.5f, 1 }, new double[] { 1, 0 },
		    new double[] { 1, 0.5f }, new double[] { 1, 1 } },
		    new double[] { 0, 1, 0, 1, 1, 1, 0, 1, 0 },
		    "Kreuz II aus Einsen", 0.2f, 0.1f, 2.4f, 5100, 8, 0),

	    // !! Kreuz III Daten !!
	    // MUSTER_ANZ : 21;
	    // DATA_IN : ((0.5,-0.25),(0.25,0), (0.5,0) ,(0.75,0) ,(0,0.25),
	    // (0.5,0.25), (1,0.25), (-0.25,0.5),(0.,0.5) ,(0.25,0.5),
	    // (0.5,0.5) ,(0.75,0.5),(1,0.5) ,(1.25,0.5),(0,0.75),
	    // (0.5,0.75) , (1,0.75) ,(0.25,1) ,(0.5,1) ,(0.75,1),
	    // (0.5,1.25));
	    // DATA_OUT : ( 0, 0, 1 , 0 , 0,
	    // 0, 0, 0 , 1 , 0,
	    // 1, 0, 1 , 0 , 0,
	    // 0, 0, 0 , 1 , 0,
	    // 0);
	    // NAME : 'Kreuz III aus Einsen';
	    // MAX_LERNRATE: 0.4;
	    // MIN_LERNRATE: 0.2;
	    // STEIGUNG: 2.5;
	    // MAX_LERNSCHRITTE: 15000;
	    // VERB_NEURONEN:5
	    // ));
	    new NNSimulaPattern(new double[][] { new double[] { 0.5f, -0.25f },
		    new double[] { 0.25f, 0 }, new double[] { 0.5f, 0 },
		    new double[] { 0.75f, 0 }, new double[] { 0, 0.25f },
		    new double[] { 0.5f, 0.25f }, new double[] { 1, 0.25f },
		    new double[] { -0.25f, 0.5f }, new double[] { 0, 0.5f },
		    new double[] { 0.25f, 0.5f }, new double[] { 0.5f, 0.5f },
		    new double[] { 0.75f, 0.5f }, new double[] { 1, 0.5f },
		    new double[] { 1.25f, 0.5f }, new double[] { 0, 0.75f },
		    new double[] { 0.5f, 0.75f }, new double[] { 1, 0.75f },
		    new double[] { 0.25f, 1 }, new double[] { 0.5f, 1 },
		    new double[] { 0.75f, 1 }, new double[] { 0.5f, 1.25f } },
		    new double[] { 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0,
			    0, 0, 0, 1, 0, 0 }, "Kreuz III aus Einsen", 0.4f,
		    0.2f, 2.5f, 15000, 5, 0) };

}
//
// $Log: $
//
