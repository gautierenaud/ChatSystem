package gui;

import main.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
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
	JButton disconnectButton;
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

		Update(ChatUserList.getInstance().GetUserList());
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
	public void Update(Vector<ChatUserInfo> userList){
		
		// clear the panel before putting items inside
		listPanel.removeAll();
		
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
					ChatGUI.getInstance().OpenChatbox(user);
				}
			});			
			userPanel.add(nameArea);
			
			listPanel.add(userPanel);
		}
		listPanel.revalidate();
		listPanel.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == disconnectButton){
			ChatGUI.getInstance().LogOut();
		}
		
	}
}
