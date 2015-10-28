package gui;

import javax.swing.*;
import java.util.*;

/*
 * base abstract Class for all the windows we are going to create for the ChatSystem
 * 
 * e.g. : UserList, ChatWindows, Notification pop-up, ...
 * 
 * peut être inutile vu qu'on a rien à mettre dedans pour le moment
 */

public abstract class ViewElement extends JFrame implements Runnable{
	
	public ViewElement(){
		initComponents();
	}
	
	abstract void initComponents();
}
