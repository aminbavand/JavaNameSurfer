/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		Entries.clear();
		update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		Entries.add(entry);
		update();
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		for (int part=0; part<=NDECADES-1; part++) {
			GLine line = new GLine(part*getWidth()/NDECADES, 0, part*getWidth()/NDECADES, getHeight());
			add(line);
			
			String str = Integer.toString(START_DECADE+part*10);
			GLabel label = new GLabel(str, part*getWidth()/NDECADES, getHeight()-5);
			add(label);					
		}

		GLine line1 = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		add(line1);

		GLine line2 = new GLine(0, getHeight()-GRAPH_MARGIN_SIZE, getWidth(), getHeight()-GRAPH_MARGIN_SIZE);
		add(line2);
		

		Color color = Color.RED;
		int colorNum = 0;
		for(NameSurferEntry entry:Entries) {
			if (colorNum==0) {
				color = Color.BLACK;
			}else if (colorNum==1) {
				color = Color.RED;		
			}else if (colorNum==2) {
				color = Color.BLUE;			
			}else {
				color = Color.MAGENTA;				
			}
			
			
			double grid_unit = (getHeight()-2*(double)GRAPH_MARGIN_SIZE)/(double)MAX_RANK;
			double x0 = 0;
			double y0 = (double)GRAPH_MARGIN_SIZE + grid_unit*(double)entry.getRank(0);


			
			String str0 = entry.getName() + " " + Integer.toString(entry.getRank(0));
			if (entry.getRank(0)==0){
				y0 = getHeight()-(double)GRAPH_MARGIN_SIZE;
				str0 = entry.getName() + " *";
			}
			GLabel label0 = new GLabel(str0, 2+x0, y0);
			label0.setColor(color);
			add(label0);
			
			for(int part=1; part<=NDECADES-1; part++) {
				double x1 = part*getWidth()/NDECADES;
				double y1 = (double)GRAPH_MARGIN_SIZE + grid_unit*(double)entry.getRank(part);

				
				String str = entry.getName() + " " + Integer.toString(entry.getRank(part));
				if (entry.getRank(part)==0){
					y1 = getHeight()-(double)GRAPH_MARGIN_SIZE;
					str = entry.getName() + " *";
				}
				GLabel label = new GLabel(str, 2+x1, y1);
				label.setColor(color);
				add(label);
				
				GLine line = new GLine(x0, y0, x1, y1);
				line.setColor(color);
				add(line);
				x0 = x1;
				y0 = y1;
			}
			
			colorNum = (colorNum+1)%4;
		
		}

		
	}
	
	
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	
	private ArrayList<NameSurferEntry> Entries = new ArrayList<NameSurferEntry>();
	
}
