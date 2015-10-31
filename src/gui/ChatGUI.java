package gui;

// ceci est un chat gui
//non ceci est une tata
public class ChatGUI {
	
	private static ChatGUI instance;

	private ChatGUI() {
		initGUI();
	}

	public static ChatGUI getInstance() {
		if (instance == null)
			instance = new ChatGUI();
		return instance;
	}
	
	
	private GUIView view;
	private GUIModel model;
	
	private void initGUI(){
		view = new GUIView(this);
		model = new GUIModel(this);
	}
	
	public void ShowMessage(String msg, String usr){
		System.out.println(msg + " from: " + usr);
	}
	
	public void OpenLogin(){
		view.OpenLoginWindow();
	}
	
	public void UserLogged(String username){
		// give the username to the controller
		System.out.println(username);
	}
	
}
