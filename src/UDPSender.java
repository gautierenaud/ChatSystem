
import java.io.*;
import java.net.*;
import common.Message;

public class UDPSender {

	private DatagramSocket senderSocket;
	
	public UDPSender(){
		try {
			senderSocket = new DatagramSocket(2041);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMsg(Message msg){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(msg);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] buf= baos.toByteArray();
		DatagramPacket packet;
		try {
			packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("10.1.255.255"), 2042);
			try {
				senderSocket.send(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Msg sent");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UDPSender sender = new UDPSender();
		UDPReceiver receiver = new UDPReceiver();
		Message msg = new Message(Message.MsgType.HELLO, "Hello les fillettes!");
		sender.sendMsg(msg);
	}

}
