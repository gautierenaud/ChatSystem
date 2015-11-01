package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

// class to display the userList to the user

/*
 * 
 * This view will be kind of the "main" window, with the disconnect button
 * clicking on the close button of this window should automatically disconnect and close the application
 * while the disconnect button will close all the current windows and open a "Login" window
 * 
 */

public class ViewUserList extends JFrame {
	
	//there should be only one instance of UserList window at any moment
	private static ViewUserList instance;

	private ViewUserList() {
		initComponents();
	}

	public static ViewUserList getInstance() {
		if (instance == null)
			instance = new ViewUserList();
		return instance;
	}
	
	private JTextArea searchArea = new JTextArea();
	private void initComponents(){
		// close everything when closed
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// overriding the close operation: we want to say goodbye!
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				// do action on disconnect (send bye)
				System.out.println("good bye");
				e.getWindow().dispose();
			}
		});
		
		this.setLayout(new GridBagLayout());
		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
		for (int i = 0; i < 20; i++) {
			JButton lala = new JButton("lala");
			lala.setAlignmentX(CENTER_ALIGNMENT);
			listPanel.add(lala);
		}
		
		GridBagConstraints scrollConst = new GridBagConstraints();
		scrollConst.fill = GridBagConstraints.BOTH;
		scrollConst.weightx = 1.0;
		scrollConst.weighty = 1.0;
		JScrollPane scroll = new JScrollPane(listPanel);
		this.add(scroll, scrollConst);
		
		/**** Search Box ****/
		// layout
		GridBagConstraints searchConst = new GridBagConstraints();
		searchConst.fill = GridBagConstraints.HORIZONTAL;
		searchConst.gridy = 1;
		searchConst.weightx = 0.0;
		searchConst.weighty = 0.0;
		this.add(searchArea, searchConst);
		
		
		/**** Disconnect Button****/
		// layout
		GridBagConstraints buttonConst = new GridBagConstraints();
		buttonConst.fill = GridBagConstraints.HORIZONTAL;
		buttonConst.gridy = 2;
		buttonConst.weightx = 0.0;
		buttonConst.weighty = 0.0;
		// button
		JButton disconnectButton = new JButton("Disconnect");
		this.add(disconnectButton, buttonConst);
		
		this.setTitle("UserList");
		this.pack();
		this.setResizable(false);
		this.setSize(200, 400);
		this.setVisible(true);
		
	}
}
