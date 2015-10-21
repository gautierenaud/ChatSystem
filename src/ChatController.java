
public class ChatController {

	// singleton pattern for ChatController
	private static ChatController instance;
	private ChatController(){}

	public static ChatController INSTANCE() {
		if (instance == null)
			instance = new ChatController();
		return instance;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
