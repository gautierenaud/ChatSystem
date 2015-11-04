package gui;

import main.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
	JPanel listPanel = new JPanel();
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

		Update(ChatUserList.getInstance().GetUserList());
		
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
		// document event listener
		searchArea.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				Update(ChatUserList.getInstance().SearchUserList(searchArea.getText()));
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				Update(ChatUserList.getInstance().SearchUserList(searchArea.getText()));
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				Update(ChatUserList.getInstance().SearchUserList(searchArea.getText()));
			}
		});
		
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
	
	// updates the content of the list panel
	public void Update(Vector<ChatUserInfo> userList){
		
		// clear the panel before putting items inside
		listPanel.removeAll();
		
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
		for (final ChatUserInfo user : userList) {
			JPanel userPanel = new JPanel();
			JTextArea nameArea = new JTextArea(user.getUsername());
			nameArea.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					ChatGUI.getInstance().OpenChatbox(user);
				}
			});
			
			userPanel.add(nameArea);
			userPanel.add(new JButton("lala"));
			JButton chat = new JButton(user.getUsername());
			chat.setSize(new Dimension(1000, 10));
			chat.setAlignmentX(CENTER_ALIGNMENT);
			listPanel.add(userPanel);
		}
		listPanel.revalidate();
		listPanel.repaint();
	}
}
