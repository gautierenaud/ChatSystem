package gui;

import main.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// class to display the userList to the user

/*
 * 
 * This view will be kind of the "main" window, with the disconnect button
 * clicking on the close button of this window should automatically disconnect and close the application
 * while the disconnect button will close all the current windows and open a "Login" window
 * 
 */

public class ViewUserList extends JFrame implements ActionListener{
	
	public ViewUserList() {
		getContentPane().setBackground(new Color(0, 128, 128));
		initComponents();
	}
	
	private JTextArea searchArea = new JTextArea();
	JPanel listPanel = new JPanel();
	JButton disconnectButton;
	private void initComponents(){
		// close everything when closed
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// overriding the close operation: we want to say goodbye!
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				// do action on disconnect (send bye)
				ChatGUI.getInstance().exit();
				e.getWindow().dispose();
			}
		});

		updateList();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 112, 10, 0};
		gridBagLayout.rowHeights = new int[]{10, 21, 5, 29, 25, 10, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		JScrollPane scroll = new JScrollPane(listPanel);
		GridBagConstraints gbc_scroll = new GridBagConstraints();
		gbc_scroll.fill = GridBagConstraints.BOTH;
		gbc_scroll.insets = new Insets(0, 0, 5, 5);
		gbc_scroll.gridx = 1;
		gbc_scroll.gridy = 1;
		getContentPane().add(scroll, gbc_scroll);
		
		//listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		//listPanel.setLayout(new FlowLayout());
		listPanel.setLayout(new VerticalFlowLayout(VerticalFlowLayout.TOP));
		// document event listener
		searchArea.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateList();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateList();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				updateList();
			}
		});
		
		GridBagConstraints gbc_searchArea = new GridBagConstraints();
		gbc_searchArea.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchArea.insets = new Insets(0, 0, 5, 5);
		gbc_searchArea.gridx = 1;
		gbc_searchArea.gridy = 3;
		getContentPane().add(searchArea, gbc_searchArea);
		// button
		disconnectButton = new JButton("Disconnect");
		disconnectButton.addActionListener(this);
		GridBagConstraints gbc_disconnectButton = new GridBagConstraints();
		gbc_disconnectButton.insets = new Insets(0, 0, 5, 5);
		gbc_disconnectButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_disconnectButton.gridx = 1;
		gbc_disconnectButton.gridy = 4;
		getContentPane().add(disconnectButton, gbc_disconnectButton);
		
		this.setTitle("UserList");
		this.pack();
		this.setResizable(false);
		this.setSize(250, 500);
		this.setVisible(true);	
	}
	
	// updates the content of the list panel
	public void updateList(){
		
		Vector<ChatUserInfo> userList = new Vector<>(); 
		userList = ChatUserList.getInstance().searchUserList(searchArea.getText());
		
		// clear the panel before putting items inside
		listPanel.removeAll();
		
		//listPanel.setLayout(new FlowLayout());
		
		for (final ChatUserInfo user : userList) {
			JPanel userPanel = new JPanel();
			
			
			JTextArea nameArea = new JTextArea(user.getUsername());
			nameArea.setColumns(15);
			nameArea.setEditable(false);
			
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
					ChatGUI.getInstance().openChatbox(user);
				}
			});
			userPanel.add(nameArea);
			
			
			// add message notification
			if (user.getUnreadCount() != 0){
				JPanel unreadPanel = new JPanel();
				JTextArea unreadArea;
				if (user.getUnreadCount() < 10)
					unreadArea = new JTextArea(Integer.toString(user.getUnreadCount()));
				else
					unreadArea = new JTextArea("+9");
				unreadArea.setEditable(false);
				unreadPanel.add(unreadArea);
				userPanel.add(unreadArea);
			}

			listPanel.add(userPanel);
		}
		listPanel.revalidate();
		listPanel.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == disconnectButton){
			ChatGUI.getInstance().logOut();
		}
		
	}
}
