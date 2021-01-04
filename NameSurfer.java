/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {
//public class NameSurfer extends ConsoleProgram implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		addInteractors();
		dataBase = new NameSurferDataBase("names-data.txt");
		graph = new NameSurferGraph();
		graph.setSize(APPLICATION_WIDTH,APPLICATION_HEIGHT);
		add(graph);
		
	}

	
	
	
	private void addInteractors() {
		nameField = new JTextField(20);
		
	
		add(new JLabel("Name"), SOUTH);	
		add(nameField,SOUTH);
		add(new JButton("Graph"), SOUTH);
		add(new JButton("Clear"), SOUTH);
		
		nameField.setActionCommand("Graph");
		nameField.addActionListener(this);	
		addActionListeners();
	}
	
/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Graph")) {
			String name = nameField.getText();
			NameSurferEntry entry = dataBase.findEntry(name);
			if (entry!=null) graph.addEntry(entry);			
			
		} else if(cmd.equals("Clear")) {	
			graph.clear();
		}
		
	}
	
	
	
	
	//instance variables:
	private JTextField nameField;
	private NameSurferDataBase dataBase;
	private NameSurferGraph graph;
	
}
