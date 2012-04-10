package de.wsdevel.neuron.nnsimula;

import static de.wsdevel.languages.turbopascal.graph.Graph.Line;
import static de.wsdevel.languages.turbopascal.graph.Graph.OutTextXY;
import static de.wsdevel.languages.turbopascal.graph.Graph.SetColor;
import static de.wsdevel.languages.turbopascal.graph.Graph.SetViewPort;
import static de.wsdevel.languages.turbopascal.graph.Graph.TextHeight;
import de.wsdevel.languages.turbopascal.graph.Graph;
import de.wsdevel.languages.turbopascal.graph.ViewPortType;

/**
 * Created on 10.04.2012 for project: SAW_NeuroN
 *
 * (c) 2012 Sebastian A. Weiﬂ - All rights reserved.
 *
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 */
public class GraphHelper {

    static void Linie(double x1, double y1, double x2, double y2) {
	if (x1 > 100) {
	    x1 = 100;
	}
	if (y1 > 100) {
	    y1 = 100;
	}
	if (x2 > 100) {
	    x2 = 100;
	}
	if (y2 > 100) {
	    y2 = 100;
	}
	if (x1 < -100) {
	    x1 = -100;
	}
	if (y1 < -100) {
	    y1 = -100;
	}
	if (x2 < -100) {
	    x2 = -100;
	}
	if (y2 < -100) {
	    y2 = -100;
	}
	int xcor1 = (int) ((NNSimulaORG.VP_Img_Px / 4) + Math
		.round((NNSimulaORG.VP_Img_Px / 2) * x1));
	if (xcor1 > 700) {
	    xcor1 = 640;
	}
	if (xcor1 < 0) {
	    xcor1 = 0;
	}
	int ycor1 = (int) Math.round((NNSimulaORG.VP_Img_Px * 0.75)
		- ((NNSimulaORG.VP_Img_Px / 2) * y1));
	if (ycor1 > 700) {
	    ycor1 = 640;
	}
	if (ycor1 < 0) {
	    ycor1 = 0;
	}
	int xcor2 = (int) ((NNSimulaORG.VP_Img_Px / 4) + Math
		.round((NNSimulaORG.VP_Img_Px / 2) * x2));
	if (xcor2 > 700) {
	    xcor2 = 640;
	}
	if (xcor2 < 0) {
	    xcor2 = 0;
	}
	int ycor2 = (int) Math.round((NNSimulaORG.VP_Img_Px * 0.75)
		- ((NNSimulaORG.VP_Img_Px / 2) * y2));
	if (ycor2 > 700) {
	    ycor2 = 640;
	}
	if (ycor2 < 0) {
	    ycor2 = 0;
	}
	Line(xcor1, ycor1, xcor2, ycor2);
    }

    /**
     * COMMENT.
     * 
     * @param y
     * @param col
     */
    static void printArrow(int y, int col) {
	SetColor(col);
	OutTextXY(10, (y + 1) * (TextHeight("1") + 5),
		String.valueOf((char) 62));
    }

    /**
     * COMMENT.
     * 
     * @param newport
     */
    static void SetPort(ViewPortType newport) {
	SetViewPort(newport.x1, newport.y1, newport.x2, newport.y2,
		Graph.ClipOn);
    }

}
//
// $Log: $
//
