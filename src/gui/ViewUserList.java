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
		
		// layout for the list
		GridBagConstraints listConst = new GridBagConstraints();
		listConst.fill = GridBagConstraints.BOTH;
		listConst.gridx = 0;
		listConst.gridy = 0;
		listConst.weightx = 1.0;
		listConst.weighty = 1.0;
		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
		for (int i = 0; i < 20; i++) {
			GridBagConstraints buttonConst = new GridBagConstraints();
			//listConst.weightx = 0.9;
			listConst.fill = GridBagConstraints.HORIZONTAL;
			listConst.weightx = 1.0;
			listConst.weighty = 1.0;
			listPanel.add(new JButton("lala"), buttonConst);
		}
		JScrollPane scroll = new JScrollPane(listPanel);
		
		
		this.add(listPanel, listConst);
		
		GridBagConstraints buttonConst = new GridBagConstraints();
		buttonConst.fill = GridBagConstraints.HORIZONTAL;
		buttonConst.gridy = 1;
		buttonConst.weightx = 0.0;
		buttonConst.weighty = 0.0;

		JButton disconnectButton = new JButton("Disconnect");
		this.add(disconnectButton, buttonConst);
		this.setTitle("UserList");
		this.pack();
		this.setResizable(false);
		this.setSize(200, 400);
		this.setVisible(true);
		
	}
}
