package gui;

public class MessageStruct{
	
	public MessageStruct(String source, String message){
		this.source = source;
		this.message = message;
	}
	
	private String source;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}