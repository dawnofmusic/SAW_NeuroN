package de.wsdevel.neuron.nnsimula;

import static de.wsdevel.languages.turbopascal.graph.Graph.Bar;
import static de.wsdevel.languages.turbopascal.graph.Graph.Circle;
import static de.wsdevel.languages.turbopascal.graph.Graph.Line;
import static de.wsdevel.languages.turbopascal.graph.Graph.OutTextXY;
import static de.wsdevel.languages.turbopascal.graph.Graph.ReadKey;
import static de.wsdevel.languages.turbopascal.graph.Graph.Rectangle;
import static de.wsdevel.languages.turbopascal.graph.Graph.SetAllPalette;
import static de.wsdevel.languages.turbopascal.graph.Graph.SetBkColor;
import static de.wsdevel.languages.turbopascal.graph.Graph.SetColor;
import static de.wsdevel.languages.turbopascal.graph.Graph.SetFillStyle;
import static de.wsdevel.languages.turbopascal.graph.Graph.SetRGBPalette;
import static de.wsdevel.languages.turbopascal.graph.Graph.SetWriteMode;
import static de.wsdevel.languages.turbopascal.graph.Graph.TextHeight;
import static de.wsdevel.languages.turbopascal.graph.Graph.TextWidth;

import java.text.DecimalFormat;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import de.wsdevel.languages.turbopascal.graph.EGAColor;
import de.wsdevel.languages.turbopascal.graph.FillStyle;
import de.wsdevel.languages.turbopascal.graph.Graph;
import de.wsdevel.languages.turbopascal.graph.PaletteType;
import de.wsdevel.languages.turbopascal.graph.ViewPortType;
import de.wsdevel.languages.turbopascal.graph.WriteMode;
import de.wsdevel.neuron.backpropagation.Net;
import de.wsdevel.neuron.backpropagation.Net.LearnResult;
import de.wsdevel.neuron.backpropagation.Neuron;

/**
 * Created on 06.04.2012.
 * 
 * for project: Java__NeuroN
 * 
 * Darmstaedter_Neuronale_Netze_Simulator Desktopv. von BAPR3_01.PAS
 * 
 * @author <a href="mailto:post@sebastian-weiss.de">Sebastian A. Weiss</a>
 * @version $Author: $ -- $Revision: $ -- $Date: $
 * 
 */
public class NNSimulaORG {

    /**
     * {@link String} COMMENT.
     */
    private static final String TITLE = "Darmstädter Neuronale-Netze-Simulator";

    /**
     * {@link DecimalFormat} COMMENT.
     */
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat(
	    "#,##0.0000000");

    static final boolean VEKTOREN = true;
    static final boolean AUSGABE = true;

    /**
     * {@link int} COMMENT.
     */
    private static final int NUMBER_OF_COLORS = 16;

    // !!Graphische Konstanten!!
    static int VP_Img_Px = 300;
    static int VP_Legend_VPx = 10;
    static int VP_Ausg_HPx = 300;
    static int VP_Ausg_VPx = 50;
    static final int Ausgf_Color = 6;
    static final int Eingf_Color = 10;

    private int aktiv_Bild;

    private int dither;
    private char ch;
    private int verkuerzung;

    // !!Graphische Variablen!!
    private final ViewPortType VP_Screen = new ViewPortType();
    private final ViewPortType VP_Legend = new ViewPortType();
    private final ViewPortType VP_Img = new ViewPortType();
    private final ViewPortType VP_Ausg = new ViewPortType();
    private final ViewPortType VP_Eing = new ViewPortType();

    /**
     * COMMENT.
     * 
     * @param x
     * @param y
     */
    public void Kreuz(double x, double y) {
	SetColor(15);
	final int xcor = (int) Math.round((VP_Img.width() * 0.25)
		+ ((VP_Img.width() * 0.5) * x));
	final int ycor = (int) Math.round((VP_Img.height() * 0.75)
		- ((VP_Img.height() * 0.5) * y));
	Line(xcor - 3, ycor, xcor + 3, ycor);
	Line(xcor, ycor - 3, xcor, ycor + 3);
    }

    /**
     * COMMENT.
     * 
     * @param x
     * @param y
     */
    public void Kreis(double x, double y) {
	SetColor(15);
	final int xcor = (int) Math.round((VP_Img.width() * 0.25)
		+ ((VP_Img.width() * 0.5) * x));
	final int ycor = (int) Math.round((VP_Img.height() * 0.75)
		- ((VP_Img.height() * 0.5) * y));
	Circle(xcor, ycor, 3);
    }

    /**
     * {@link Log} COMMENT.
     */
    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog(NNSimulaORG.class);

    /**
     * {@link Color[]} COMMENT.
     */
    public static final EGAColor[] EGAColors = new EGAColor[] {
	    // Black 0
	    new EGAColor(0, 0, 0),
	    // Blue 1
	    new EGAColor(0, 0, 49),
	    // Green 2
	    new EGAColor(0, 49, 0),
	    // Cyan 3
	    new EGAColor(0, 42, 42),
	    // Red 4
	    new EGAColor(63, 0, 0),
	    // Magenta 5
	    new EGAColor(49, 0, 49),
	    // Brown 6
	    new EGAColor(30, 16, 0),
	    // LightGray 7
	    new EGAColor(49, 49, 49),
	    // Darkgray 8
	    new EGAColor(8, 8, 8),
	    // LightBlue 9
	    new EGAColor(0, 0, 63),
	    // LightGreen 10
	    new EGAColor(0, 63, 0),
	    // LightCyan 11
	    new EGAColor(0, 52, 52),
	    // LightRed 12
	    new EGAColor(63, 20, 20),
	    // LightMagenta 13
	    new EGAColor(63, 0, 63),
	    // Yellow 14
	    new EGAColor(63, 63, 0),
	    // White 15
	    new EGAColor(63, 63, 63) };

    /**
     * {@link byte[]} COMMENT.
     */
    private static final byte[] Graypal = new byte[] { 0, 5, 8, 11, 14, 17, 20,
	    24, 28, 32, 36, 40, 45, 50, 56, 63 };

    /**
     * COMMENT.
     * 
     * starten mit "NNSIMULA [verkürzung] [feinheit]", wobei 'verkürzung' eine
     * Zahl darstellt, um die alle Lernzeiten verringert werden und feinheit die
     * Auflösung der graphischen Darstellung verändert (1=maximale Auflösung).
     * Standardeinstellungen sind verkuerzung=0 und feinheit=1 (anzuwenden auf
     * langsamen Rechnern).
     * 
     * @param args
     */
    public static void main(String[] args) {
	new NNSimulaORG(args.length > 0 ? args[0] : null,
		args.length > 1 ? args[1] : null).doIt();
    }

    private Net net;

    /**
     * {@link NNSimulaPattern} COMMENT.
     */
    private NNSimulaPattern currentPattern = NNSimulaPatterns.MUSTER_REC[0];

    /**
     * COMMENT.
     */
    private NNSimulaORG(String param1, String param2) {
	BasicConfigurator.configure();
	Logger.getRootLogger().setLevel(Level.WARN);

	Graph.getInstance().showUI(TITLE);

	if (param1 != null) {
	    this.verkuerzung = Integer.getInteger(param1);
	} else {
	    this.verkuerzung = 0;
	}
	if (param2 != null) {
	    this.dither = Integer.getInteger(param2);
	} else {
	    this.dither = 10;
	}

	this.aktiv_Bild = 0;
	setCurrentPattern(NNSimulaPatterns.MUSTER_REC[this.aktiv_Bild]);
	initUI();
	StartDialog();

	// if (Constants.INPUT_NEURONS_NUMBER != 2) {
	// System.out.println();
	// System.out.println();
	// System.out
	// .println(" Die Eingaenge des Netzes sind nicht zweidimensional.");
	// System.out
	// .println(" Bitte setzen Sie die Konstante \"EINGAENGE\" auf 2, da");
	// System.out
	// .println(" die Gewichte sonst nicht als Vektoren im zweidimen-");
	// System.out.println(" sionalen Raum dargestellt werden k”nnen.");
	// try {
	// this.ch = (char) System.in.read();
	// } catch (final IOException e) {
	// NNSIM2_2.LOG.error(e.getLocalizedMessage(),
	// NNSIM2_2.LOG.isDebugEnabled() ? e : null);
	// }
	// System.exit(255);
	// }
    }

    /**
     * COMMENT.
     */
    private void printOutput() {
	GraphHelper.SetPort(this.VP_Screen);
	SetColor(13);
	Rectangle((this.VP_Ausg.x1 - 1), (this.VP_Ausg.y1 - 1),
		(this.VP_Ausg.x2), (this.VP_Ausg.y2));
	GraphHelper.SetPort(this.VP_Ausg);
	SetFillStyle(FillStyle.SolidFill, NNSimulaORG.Ausgf_Color);
	Bar(0, 0, NNSimulaORG.VP_Ausg_HPx, NNSimulaORG.VP_Ausg_VPx);
	SetColor(10);
	OutTextXY(5, 11, " Lernschritte: ");
	OutTextXY(5, 21, " Fehler: ");
	OutTextXY(5, 31, " Lernrate: ");
    }

    void Destruct() {
	// ClearDevice;
	// SetAllPalette(EGAPalette);
	// CloseGraph;
	// TextMode(LastMode);
	System.out.println("Bye, Bye.");
	System.out.println();
	System.exit(0);
    }

    /**
     * COMMENT.
     */
    void doIt() {
	do {
	    LearnResult lr = this.net.learn(this.currentPattern);
	    printLearnResultToOutput(lr);
	    // printNetOutputMap(this.dither);
	    printNetOutputMap(8);
	    // this.ch = Read_A_Key();
	} while (true);
	// Destruct();
    }

    /**
     * COMMENT.
     * 
     * @param dither
     */
    private void printNetOutputMap(final int dither) {
	GraphHelper.SetPort(this.VP_Img);
	final double deltaX = 2.0d / VP_Img.width();
	final double deltaY = 2.0d / VP_Img.height();
	final double halfWidth = VP_Img.width() / 2.0d;
	final double halfHeight = VP_Img.height() / 2.0d;
	double xValue = -0.50;
	do {
	    double yValue = -0.50;
	    do {
		double output = this.net
			.getOutputFromFirstOutputNeuronForInput(new double[] {
				xValue, yValue });
		final int xCoor = (int) Math.round((xValue + 0.5) * halfWidth);
		final int yCoor = (int) (VP_Img.height() - 1 - Math
			.round((yValue + 0.5) * halfHeight));
		SetFillStyle(FillStyle.SolidFill,
			(int) Math.round((NUMBER_OF_COLORS - 1) * output));
		Bar(xCoor, yCoor, xCoor + dither, yCoor + dither);
		yValue += (dither * deltaY);
	    } while (yValue < 1.5);
	    xValue += (dither * deltaX);
	} while (xValue < 1.5);
	printResultImage(NNSimulaORG.AUSGABE, NNSimulaORG.VEKTOREN);
    }

    private void printLearnResultToOutput(LearnResult lr) {
	printOutput();
	GraphHelper.SetPort(this.VP_Ausg);
	SetColor(10);
	OutTextXY(120, 11, Long.toString(lr.stepsNeeded));
	OutTextXY(120, 21,
		NNSimulaORG.DECIMAL_FORMAT.format(lr.standardDeviation));
	OutTextXY(120, 31,
		NNSimulaORG.DECIMAL_FORMAT.format(this.net.getLearningRate()));
    }

    private void printInput() {
	GraphHelper.SetPort(this.VP_Screen);
	SetColor(13);
	Rectangle((this.VP_Eing.x1 - 1), (this.VP_Eing.y1 - 1),
		(this.VP_Eing.x2), (this.VP_Eing.y2));
	GraphHelper.SetPort(this.VP_Eing);
	SetFillStyle(FillStyle.SolidFill, NNSimulaORG.Eingf_Color);
	Bar(0, 0, VP_Eing.width(), VP_Eing.height());
    }

    /**
     * COMMENT.
     */
    private void printBackground() {
	GraphHelper.SetPort(this.VP_Screen);
	SetFillStyle(FillStyle.SolidFill, 13);
	Bar(0, (Graph.GetMaxY - Graph.GetMaxY / 24), Graph.GetMaxX,
		Graph.GetMaxY);
	Bar(0, 0, Graph.GetMaxX, Graph.GetMaxY / 24);

	final int y_cor = (int) Math
		.round(((Graph.GetMaxY / 24d) + TextHeight("X")) / 2d);
	SetColor(3);
	OutTextXY((int) Math.round((Graph.GetMaxX - TextWidth(TITLE)) / 2d),
		y_cor, TITLE);
	OutTextXY(0, (int) Math.round(y_cor + (23 * Graph.GetMaxY / 24d)),
		" Es wird gelernt: Taste => Abbruch / <XX> => Ende");
    }

    private double Gerade(int layerIndex, int neuronIndex, double x) {
	// SEBASTIAN take care of more than one hidden layer
	final Neuron neuron = this.net.getNeuron(layerIndex, neuronIndex);
	System.out.println(Arrays.toString(neuron.weights));
	if (neuron.weights.length > 2) {
	    return -((neuron.weights[1] * x) + neuron.weights[0])
		    / neuron.weights[2];
	}
	return 0;
    }

    /**
     * @return {@link NNSimulaPattern} the currentPattern.
     */
    public NNSimulaPattern getCurrentPattern() {
	return this.currentPattern;
    }

    /**
     * COMMENT.
     */
    private void initUI() {
	// !! Setzen der Koordinaten des Bildfensters
	NNSimulaORG.VP_Img_Px = Math.round(NNSimulaORG.VP_Img_Px
		* Graph.GetMaxY / 480);
	this.VP_Img.x1 = Math.round(Graph.GetMaxY / 24f);
	this.VP_Img.y1 = Math.round(Graph.GetMaxY - NNSimulaORG.VP_Img_Px
		- (Graph.GetMaxY / 24)) - 4;
	this.VP_Img.x2 = Math.round(NNSimulaORG.VP_Img_Px
		+ (Graph.GetMaxY / 24));
	this.VP_Img.y2 = Math.round(Graph.GetMaxY - (2 * (Graph.GetMaxY / 24)));

	// !! Setzen der Koordinaten des Eingabefensters
	this.VP_Eing.x1 = (Graph.GetMaxX + 1)
		- Math.round((280 * ((Graph.GetMaxX + 1))) / 640)
		- ((Graph.GetMaxY + 1) / 24);
	this.VP_Eing.y1 = 2 * ((Graph.GetMaxY + 1) / 24);
	this.VP_Eing.x2 = (Graph.GetMaxX + 1) - ((Graph.GetMaxY + 1) / 24);
	this.VP_Eing.y2 = (Graph.GetMaxY + 1)
		- (2 * ((Graph.GetMaxY + 1) / 24));

	// !! Setzen der Koordinaten des Ausgabefensters
	NNSimulaORG.VP_Ausg_HPx = Math
		.round((NNSimulaORG.VP_Ausg_HPx * (Graph.GetMaxX + 1)) / 640);
	NNSimulaORG.VP_Ausg_VPx = Math
		.round((NNSimulaORG.VP_Ausg_VPx * (Graph.GetMaxY + 1)) / 480);
	this.VP_Ausg.x1 = Math.round((Graph.GetMaxY + 1) / 24);
	this.VP_Ausg.y1 = 2 * Math.round((Graph.GetMaxY + 1) / 24);
	this.VP_Ausg.x2 = Math.round(NNSimulaORG.VP_Ausg_HPx
		+ ((Graph.GetMaxY + 1) / 24));
	this.VP_Ausg.y2 = (2 * Math.round((Graph.GetMaxY + 1) / 24))
		+ NNSimulaORG.VP_Ausg_VPx;

	NNSimulaORG.VP_Legend_VPx = Math
		.round((NNSimulaORG.VP_Legend_VPx * (Graph.GetMaxY + 1)) / 480);
	this.VP_Legend.x1 = Math.round((Graph.GetMaxY + 1) / 24);
	this.VP_Legend.y1 = Math.round((Graph.GetMaxY + 1)
		- NNSimulaORG.VP_Img_Px - (2 * ((Graph.GetMaxY + 1) / 24))) - 4;
	this.VP_Legend.x2 = Math.round(NNSimulaORG.VP_Img_Px
		+ ((Graph.GetMaxY + 1) / 24));
	this.VP_Legend.y2 = (Math.round((Graph.GetMaxY + 1)
		- NNSimulaORG.VP_Img_Px - (2 * ((Graph.GetMaxY + 1) / 24))) - 4)
		+ NNSimulaORG.VP_Legend_VPx;

	this.VP_Screen.x1 = 0;
	this.VP_Screen.y1 = 0;
	this.VP_Screen.x2 = Graph.GetMaxX;
	this.VP_Screen.y2 = Graph.GetMaxY;

	// !! Setzen der Palette
	final PaletteType EGAPalette = new PaletteType();
	EGAPalette.Size = NNSimulaORG.NUMBER_OF_COLORS;

	for (int zler = 0; zler < NNSimulaORG.NUMBER_OF_COLORS; zler++) {
	    EGAPalette.Colors[zler] = zler + NNSimulaORG.NUMBER_OF_COLORS;
	}
	for (int zler = 0; zler < NNSimulaORG.NUMBER_OF_COLORS; zler++) {
	    SetRGBPalette(zler, NNSimulaORG.EGAColors[zler].egaRed,
		    NNSimulaORG.EGAColors[zler].egaGreen,
		    NNSimulaORG.EGAColors[zler].egaBlue);
	}

	final PaletteType GrayPalette = new PaletteType();
	GrayPalette.Size = NNSimulaORG.NUMBER_OF_COLORS;
	for (int zler = 0; zler < NNSimulaORG.NUMBER_OF_COLORS; zler++) {
	    GrayPalette.Colors[zler] = zler;
	}
	for (int zler = 0; zler < NNSimulaORG.NUMBER_OF_COLORS; zler++) {
	    SetRGBPalette(zler, NNSimulaORG.Graypal[zler],
		    NNSimulaORG.Graypal[zler], NNSimulaORG.Graypal[zler]);
	}

	// {!! Hier ist wählbar zwischen den Standard-EGA-Farben (EGAPalette)
	// oder 16 Graustufen (GrayPalette) }
	SetAllPalette(GrayPalette);
	// SetAllPalette(EGAPalette);

	// {!! Setzen der Standardschrift und ihrer Einstellungen }
	// SetTextStyle(DefaultFont, HorizDir, 1);

	// {!! Setzen der Hintergrundfarbe }
	SetBkColor(0);

	// {!! Setzen der LinienEinstellungen }
	// SetLineStyle(SolidLn, 1, NormWidth);
	printBackground();
	printResultImage(!NNSimulaORG.AUSGABE, !NNSimulaORG.VEKTOREN);
	printLegend();
	printOutput();
    }

    /**
     * COMMENT.
     */
    private void printLegend() {
	GraphHelper.SetPort(this.VP_Screen);
	SetColor(3);
	OutTextXY(
		Math.round((Graph.GetMaxY + 1) / 24),
		Math.round((Graph.GetMaxY + 1) - NNSimulaORG.VP_Img_Px
			- (2 * ((Graph.GetMaxY + 1) / 24))) - 14, "0");
	OutTextXY(
		(int) Math.round(((NNSimulaORG.VP_Img_Px - TextWidth("0.5")) / 2)
			+ this.VP_Legend.x1), Math.round((Graph.GetMaxY + 1)
			- NNSimulaORG.VP_Img_Px
			- (2 * ((Graph.GetMaxY + 1) / 24))) - 14, "0.5");
	OutTextXY(
		(int) Math.round((NNSimulaORG.VP_Img_Px - TextWidth("1"))
			+ this.VP_Legend.x1),
		Math.round((Graph.GetMaxY + 1) - NNSimulaORG.VP_Img_Px
			- (2 * ((Graph.GetMaxY + 1) / 24))) - 14, "1");
	SetColor(13);
	Rectangle((this.VP_Legend.x1 - 1), (this.VP_Legend.y1 - 1),
		this.VP_Legend.x2, this.VP_Legend.y2);

	GraphHelper.SetPort(this.VP_Legend);
	for (int color = 0; color < NNSimulaORG.NUMBER_OF_COLORS; color++) {
	    SetFillStyle(FillStyle.SolidFill, color);
	    Bar((int) Math
		    .round(color
			    * (NNSimulaORG.VP_Img_Px / (double) NNSimulaORG.NUMBER_OF_COLORS)),
		    0,
		    (int) Math
			    .round((color + 1)
				    * (NNSimulaORG.VP_Img_Px / (double) NNSimulaORG.NUMBER_OF_COLORS)),
		    NNSimulaORG.VP_Legend_VPx);
	}
    }

    /**
     * COMMENT.
     * 
     * @param ausgabe
     * @param vektoren
     */
    private void printResultImage(boolean ausgabe, boolean vektoren) {
	GraphHelper.SetPort(this.VP_Screen);
	SetColor(13);
	Rectangle((this.VP_Img.x1 - 1), (this.VP_Img.y1 - 1), (this.VP_Img.x2),
		(this.VP_Img.y2));
	GraphHelper.SetPort(this.VP_Img);
	SetColor(8);
	SetWriteMode(WriteMode.XorPut);
	if (vektoren) {
	    // for (int layerIndex = 1; layerIndex <
	    // this.net.getNumberOfLayers(); layerIndex++) {
	    // int layer = this.net.getNumberOfLayers() - 1;
	    // for (int neuronIndex = 0; neuronIndex < this.net
	    // .getNumberOfNeuronsInLayer(layer); neuronIndex++) {
	    // GraphHelper.Linie(-0.5, Gerade(layer, neuronIndex, -0.5), 1.5,
	    // Gerade(layer, neuronIndex, 1.5));
	    // }
	    // }
	} else {
	    Graph.ClearViewPort();
	}
	SetWriteMode(WriteMode.XorPut);
	for (int vectorIndex = 0; vectorIndex < this.currentPattern.inputVectors.length; vectorIndex++) {
	    if (this.currentPattern.targetOutputVector[vectorIndex] < 0.5) {
		Kreis(this.currentPattern.inputVectors[vectorIndex][0],
			this.currentPattern.inputVectors[vectorIndex][1]);
	    } else {
		Kreuz(this.currentPattern.inputVectors[vectorIndex][0],
			this.currentPattern.inputVectors[vectorIndex][1]);
	    }
	}
	SetWriteMode(WriteMode.NormalPut);
    }

    /**
     * COMMENT.
     * 
     * @return <code>char</code>
     */
    private char Read_A_Key() {
	char key = 0;
	while (key == 0) {
	    key = ReadKey();
	    if (key == 68) {
		Destruct();
	    }
	}
	return key;
    }

    /**
     * @param currentPattern
     *            {@link NNSimulaPattern} the currentPattern to set.
     */
    public void setCurrentPattern(final NNSimulaPattern currentPattern) {
	this.currentPattern = currentPattern;
	initNetForPattern(this.currentPattern);
    }

    /**
     * COMMENT.
     * 
     * @param pattern
     */
    private void initNetForPattern(final NNSimulaPattern pattern) {
	this.net = Net.createNet(Constants.INPUT_NEURONS_NUMBER,
		Constants.NUMBER_OF_HIDDEN_LAYERS, pattern.VERB_NEURONEN,
		Constants.OUTPUT_NEURONS_NUMBER);
	this.net.setMaxLearningRate(pattern.MAX_LERNRATE);
	this.net.setMinLearningRate(pattern.MIN_LERNRATE);
	this.net.setMaxIterations(pattern.MAX_LERNSCHRITTE);
	this.net.setGradient(pattern.gradient);
    }

    /**
     * COMMENT.
     */
    private void StartDialog() {
	printInput();
	final int hoehe = (TextHeight("1") + 5);
	SetColor(3);
	for (int muster = 0; muster < NNSimulaPatterns.MUSTER_REC.length; muster++) {
	    OutTextXY(20, (muster + 1) * hoehe, muster + ": "
		    + NNSimulaPatterns.MUSTER_REC[muster].getName());
	}
	GraphHelper.printArrow(this.aktiv_Bild, 3);
	this.dither = 1;
	do {
	    SetColor(3);
	    this.ch = Read_A_Key();
	    GraphHelper.SetPort(this.VP_Eing);
	    SetFillStyle(FillStyle.SolidFill, NNSimulaORG.Eingf_Color);
	    Bar(9, (NNSimulaPatterns.MUSTER_REC.length + 2) * hoehe,
		    VP_Eing.width(), (NNSimulaPatterns.MUSTER_REC.length + 4)
			    * hoehe);
	    switch (this.ch) {
	    case '-':
		GraphHelper.SetPort(this.VP_Eing);
		if (this.dither < 10) {
		    this.dither++;
		}
		SetColor(7);
		OutTextXY(10, (NNSimulaPatterns.MUSTER_REC.length + 2) * hoehe,
			"Auflösung=" + this.dither);
		break;
	    case '+':
		GraphHelper.SetPort(this.VP_Eing);
		if (this.dither > 1) {
		    this.dither--;
		}
		SetColor(7);
		OutTextXY(10, (NNSimulaPatterns.MUSTER_REC.length + 2) * hoehe,
			"Auflösung=" + this.dither);
		break;
	    case 'r':
		GraphHelper.SetPort(this.VP_Eing);
		GraphHelper
			.printArrow(this.aktiv_Bild, NNSimulaORG.Eingf_Color);
		this.aktiv_Bild--;
		if (this.aktiv_Bild < 0) {
		    this.aktiv_Bild = NNSimulaPatterns.MUSTER_REC.length - 1;
		}
		setCurrentPattern(NNSimulaPatterns.MUSTER_REC[this.aktiv_Bild]);
		GraphHelper.printArrow(this.aktiv_Bild, 3);
		printResultImage(!NNSimulaORG.AUSGABE, !NNSimulaORG.VEKTOREN);
		break;
	    case 'v':
		GraphHelper.SetPort(this.VP_Eing);
		GraphHelper
			.printArrow(this.aktiv_Bild, NNSimulaORG.Eingf_Color);
		this.aktiv_Bild++;
		if (this.aktiv_Bild >= NNSimulaPatterns.MUSTER_REC.length) {
		    this.aktiv_Bild = 0;
		}
		setCurrentPattern(NNSimulaPatterns.MUSTER_REC[this.aktiv_Bild]);
		GraphHelper.printArrow(this.aktiv_Bild, 3);
		GraphHelper.SetPort(this.VP_Img);
		printResultImage(!NNSimulaORG.AUSGABE, !NNSimulaORG.VEKTOREN);
		break;
	    case 'S':
		break;
	    default:
		GraphHelper.SetPort(this.VP_Eing);
		SetColor(7);
		OutTextXY(10, (NNSimulaPatterns.MUSTER_REC.length + 2) * hoehe,
			"<+>/<-> bzw. <v>/<r> zum Auswählen,");
		OutTextXY(10, (NNSimulaPatterns.MUSTER_REC.length + 3) * hoehe,
			"starten mit <S>");
	    }
	} while (this.ch != 'S');
    }
}
//
// $Log: $
//
