package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ViewChatbox extends JFrame implements ActionListener{
	
	// multiton pattern
	private static Map<String, ViewChatbox> chatboxList = new HashMap<String, ViewChatbox>();
	
	private ViewChatbox(String name){
		initComponents(name);
	}
	
	public static ViewChatbox getInstance(String name){
		if (!chatboxList.containsKey(name)){
			chatboxList.put(name, new ViewChatbox(name));
		}
		return chatboxList.get(name);
	}
	
	// update the content of a chatbox when he/she received a message
	public void UpdateBox(String source){
		if (chatboxList.containsKey(source)){
			chatboxList.get(source).Update();
		}
	}
	
	private void Update(){
		GUIModel.getInstance().getMessages(opponentName);
	}
	
	private String opponentName;
	private TextField messageZone;
	private TextField sendZone;
	private Button sendButton;
	private void initComponents(String name){
		// set the default behavior when closing
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// set the layout of the login windows
		this.setLayout(new GridLayout(2,1));
		
		opponentName = name;
		Vector<MessageStruct> conversationList = GUIModel.getInstance().getMessages(opponentName);
		
		/*
		// a new text field with 30 columns
		text = new JTextField(30);
		// a new button identified as OK
		button = new JButton("Login");
		button.addActionListener(this);
		// places the components in the layout
		this.add(label);
		this.add(text);
		this.add(button);*/
		// packs the fenetre: size is calculated
		// regarding the added components
		this.setTitle(name);
		this.pack();
		this.setSize(400,70);
		// the JFrame is visible now
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
