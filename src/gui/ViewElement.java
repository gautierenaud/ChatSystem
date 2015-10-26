package gui;

import javax.swing.*;
import java.awt.*;

/*
 * base abstract Class for all the windows we are going to create for the ChatSystem
 * 
 * e.g. : UserList, ChatWindows, Notification pop-up, ...
 * 
 */

public class ViewElement extends JFrame {
	
	public ViewElement(){
		initComponents();
	}
	
	// initialize the view (abstract?)
	private void initComponents() {
		/*
		// create the components
		// a new label with the "Nom" as value
		label = new JLabel("Nom: ");
		// a new text field with 20 columns
		text = new JTextField(20);
		// a new button identified as OK
		button = new JButton("OK");
		// configures the JFrame layout using a border layout
		this.setLayout(new BorderLayout());
		// places the components in the layout
		this.add("West",label);
		this.add("Center",text);
		this.add("East",button);
		// packs the fenetre: size is calculated
		// regarding the added components
		this.pack();
		// the JFrame is visible now
		this.setVisible(true);
		*/
	}

}
