package common;
import java.io.Serializable;


public class Message implements Serializable {

	public enum MsgType {HELLO, HELLO_REPLY, TEXT_MESSAGE, BYE, FILE_REQUEST, FILE_ACCEPT, FILE_REFUSE};

	private MsgType msgType;
	private String textContent;
	private String sender; // celui qui a envoyé le message, l'IP étant récupérée via le datagram (peut être inutile)
	
	public Message(MsgType type, String content, String sender){
		this.msgType = type;
		this.textContent = content;
		this.sender = sender;
	}
	
	public Message(MsgType type, String content){
		this.msgType = type;
		this.textContent = content;
		this.sender = "";
	}
	
	public String getContent(){
		return textContent;
	}
	
	public MsgType getType(){
		return msgType;
	}

	public String getSender() {
		return sender;
	}	
	
	@Override
	public String toString(){
		return "Message with type: " + msgType + " from " + sender + " content: " + textContent;
	}
}