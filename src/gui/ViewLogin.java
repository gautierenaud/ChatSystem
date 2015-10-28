package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewLogin extends ViewElement implements ActionListener{

	private JLabel label;
	private JTextField text;
	private JButton button;
	
	public ViewLogin(){
	}
	
	@Override
	void initComponents() {
		
		// set the default behavior when closing
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// set the layout of the login windows
		this.setLayout(new GridLayout(1,3));
		
		// a new label with the "Nom" as value
		label = new JLabel("Username: ");
		// a new text field with 30 columns
		text = new JTextField(30);
		// a new button identified as OK
		button = new JButton("Login");
		button.addActionListener(this);
		// places the components in the layout
		this.add(label);
		this.add(text);
		this.add(button);
		// packs the fenetre: size is calculated
		// regarding the added components
		this.pack();
		this.setSize(400,70);
		// the JFrame is visible now
		this.setVisible(true);
	}
	
	public void run(){
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ((e.getSource() == button) && (text.getText().length() != 0)){
			ChatGUI.getInstance().UserLogged(text.getText());
			// close the login windows
			this.setVisible(false);
			this.dispose();
		}
		
	}
}