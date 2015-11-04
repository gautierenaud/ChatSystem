package gui;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

import java.util.*;

import main.*;

public class ViewChatbox extends JFrame implements ActionListener{
	
	// multiton pattern
	private static Map<String, ViewChatbox> chatboxList = new HashMap<String, ViewChatbox>();
	
	private ViewChatbox(ChatUserInfo info){
		initComponents(info);
	}
	
	public static ViewChatbox getInstance(ChatUserInfo info){
		if (!chatboxList.containsKey(info.getUserID())){
			chatboxList.put(info.getUserID(), new ViewChatbox(info));
		}
		return chatboxList.get(info.getUserID());
	}
	
	private void removeInstance(String id){
		chatboxList.remove(id);
	}
	
	// update the content of a chatbox when he/she received a message
	public void UpdateBox(String source){
		if (chatboxList.containsKey(source)){
			chatboxList.get(source).Update();
		}
	}
	
	private void Update(){
		GUIModel.getInstance().getMessages(opponentID);
	}
	
	private String opponentID;
	private String opponentName;
	private JTextPane messageZone = new JTextPane();
	private JScrollPane messageScroll;
	private JPanel bottomPanel;
	private JTextField sendZone;
	private Button sendButton;
	
	private void initComponents(ChatUserInfo info){
		// set the default behavior when closing
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// set the layout of the login windows
		this.setLayout(new GridLayout(2,1));
		
		// remove the instance when closing
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				removeInstance(opponentID);
			}
		});
		
		opponentID = info.getUserID();
		opponentName = info.getUsername();
		
		// write the content of the conversation to the ChatBox
		messageZone.setEditable(false);

		Vector<MessageStruct> conversationList = GUIModel.getInstance().getMessages(opponentID);
		if (conversationList != null){
			for(MessageStruct message: conversationList){
				AddMessage(message.getSource(), message);
			}
		}
		messageScroll = new JScrollPane(messageZone);
		
		this.add(messageScroll);
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		sendZone = new JTextField(20);
		sendButton = new Button("Send");
		sendButton.addActionListener(this);
		bottomPanel.add(sendZone);
		bottomPanel.add(sendButton);
		
		this.add(bottomPanel);
		// packs the fenetre: size is calculated
		// regarding the added components
		this.setTitle(opponentName);
		this.pack();
		this.setSize(400,70);
		// the JFrame is visible now
		this.setVisible(true);
	}
	
	public void AddMessage(String source, MessageStruct message){
		String userName = ChatGUI.getInstance().GetUserName();
		
		// this line was for testing
		//String userName = "rgautier";
		
		StyledDocument conversation = messageZone.getStyledDocument();
		
		// defining the style attributes for left and right
		SimpleAttributeSet left = new SimpleAttributeSet();
		StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
		
		SimpleAttributeSet right = new SimpleAttributeSet();
		StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
		
		try{
			int length = conversation.getLength();
			conversation.insertString(length, message.getMessage() + "\n", null);
			if (message.getSource() == userName)
				conversation.setParagraphAttributes(length + 1, 1, right, false);
			else
				conversation.setParagraphAttributes(length + 1, 1, left, false);
		}catch (Exception e){
			System.err.println(e);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sendButton){
			// do something to send message
			
			// clear the send zone
			sendZone.setText("");
		}
		
	}	
}
