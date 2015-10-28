package gui;

// send commands to the model to update the model's state
// send commands to the view to update it's presentation

public class GUIController {
	
	private static GUIController instance;

	private GUIController() {
	}

	public static GUIController getInstance() {
		if (instance == null)
			instance = new GUIController();
		return instance;
	}
	
}
