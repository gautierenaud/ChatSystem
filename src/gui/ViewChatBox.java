package gui;

import main.*;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ViewChatBox implements ActionListener {

	private ChatGUI controller;
	private GUIView view;
	
	public ViewChatBox(ChatUserInfo info){
		InitializeStyle();
		Initialize(info);
		view = GUIView.getInstance();
		controller = ChatGUI.getInstance();
	}
		
	/**
	 * Initialize the contents of the frame.
	 */
	
	private String id;
	private String sourceName;
	private JTextPane messageArea;
	private JButton sendButton;
	private JFrame frame;
	private JTextField sendText;
	
	private void Initialize(ChatUserInfo info) {
		
		sourceName = info.getUsername();
		id = info.getUserID();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 471, 316);
		frame.setTitle(sourceName);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// add event listener: remove this one when closing
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				view.CloseChatBox(id);
			}
		});
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 321, 0, 10, 0};
		gridBagLayout.rowHeights = new int[]{10, 218, 0, 10, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		frame.getContentPane().add(scrollPane, gbc_scrollPane);
		
		messageArea = new JTextPane();
		messageArea.setEditable(false);
		scrollPane.setViewportView(messageArea);
		DisplayAllMessages();
		
		sendText = new JTextField();
		GridBagConstraints gbc_sendText = new GridBagConstraints();
		gbc_sendText.insets = new Insets(0, 0, 5, 5);
		gbc_sendText.fill = GridBagConstraints.HORIZONTAL;
		gbc_sendText.gridx = 1;
		gbc_sendText.gridy = 2;
		frame.getContentPane().add(sendText, gbc_sendText);
		sendText.setColumns(10);
		
		sendButton = new JButton("Send");
		sendButton.addActionListener(this);
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.insets = new Insets(0, 0, 5, 5);
		gbc_btnSend.gridx = 2;
		gbc_btnSend.gridy = 2;
		frame.getContentPane().add(sendButton, gbc_btnSend);
		frame.setVisible(true);
	}
	
	private SimpleAttributeSet right = new SimpleAttributeSet();
	private SimpleAttributeSet left = new SimpleAttributeSet();
	private void InitializeStyle(){
		StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
		StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
	}
	
	public void AppendMessage(MessageStruct message){
		SimpleAttributeSet tmp = new SimpleAttributeSet();
		
		if (message.getSource() == sourceName)
			tmp = left;
		else
			tmp = right;
		
		// append message at the end of the document
		StyledDocument doc = messageArea.getStyledDocument();
		try
		{
		    int length = doc.getLength();
		    doc.insertString(doc.getLength(), "\n" + message.getMessage(), null);
		    doc.setParagraphAttributes(length+1, 1, tmp, false);
		}
		catch(Exception e) { System.out.println(e);}
	}
	
	private void DisplayAllMessages(){
		Vector<MessageStruct> messages = GUIModel.getInstance().getMessages(id);
		if (messages != null){
			for (MessageStruct msg : messages){
				AppendMessage(msg);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sendButton){
			// send Message
			controller.SendMessage(sendText.getText(), id);
			sendText.setText("");
		}
	}
}
