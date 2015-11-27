package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewLogin extends JFrame implements ActionListener{

	private JLabel label;
	private JTextField text;
	private JButton button;
	
	public ViewLogin(){
		initComponents();
	}
	
	void initComponents() {
		
		// set the default behavior when closing
		// will exit the application itself!!
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// set the layout of the login windows
		this.setLayout(new GridLayout(1,3));
		
		// a new label with the "Nom" as value
		label = new JLabel("Username: ");
		// a new text field with 30 columns
		text = new JTextField(30);
		text.addActionListener(this);
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
		this.setResizable(false);
		// the JFrame is visible now
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ((e.getSource() == button) && (text.getText().length() != 0)){
			ChatGUI.getInstance().userLogged(text.getText());
			// close the login windows
			this.setVisible(false);
			this.dispose();
		}else if ((e.getSource() == text) && (text.getText().length() != 0)){
			ChatGUI.getInstance().userLogged(text.getText());
			// close the login windows
			this.setVisible(false);
			this.dispose();
		}
	}
}
