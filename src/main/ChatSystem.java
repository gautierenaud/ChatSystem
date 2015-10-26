package main;


public class ChatSystem {

	/**
	 * @param args
	 */
	
	private static ChatController controller;
	
	public static void main(String[] args) {
		
		controller = ChatController.getInstance();
		controller.initAll();
		
	}

}
