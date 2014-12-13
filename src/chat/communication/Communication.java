package chat.communication;
import java.net.SocketException;

public class Communication {
	private Microphone mic;
	private Receiver rec;
	/**
	 * @param args
	 * @throws SocketException 
	 */
	public static void main(String[] args) throws SocketException {
		Communication c=new Communication("192.168.0.103", 9000, 9001);
		c.mic.start();
		c.rec.start();
	}
	/**
	 * @param mic
	 * @param rec
	 * @throws SocketException 
	 */
	public Communication(String IP, int inPort,int outPort) throws SocketException {
		super();
		this.mic=new Microphone(IP, outPort);
		this.rec=new Receiver(IP, inPort);
	}
}