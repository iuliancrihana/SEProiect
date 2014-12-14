package chat.communication;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Communication {
	private final Microphone mic;
	private final Receiver rec;

	private DatagramSocket sendSock;
	private DatagramSocket receiveSock;

	/**
	 * @param args
	 * @throws SocketException
	 */
	public static void main(String[] args) throws SocketException {

		Communication c = new Communication("192.168.0.103", 5000, 5001);
		c.mic.start();
		c.rec.start();
		
		//daca inchid socketul...o sa stiu ca s-a terminat conexiunea
		//c.sendSock.close();
		//c.receiveSock.close();
	}

	/**
	 * @param mic
	 * @param rec
	 * @throws SocketException
	 */
	public Communication(String IP, int inPort, int outPort)
			throws SocketException {
		super();
		// this.closeVar = false;
		sendSock = new DatagramSocket(outPort);
		receiveSock = new DatagramSocket(inPort);
		this.mic = new Microphone(IP, outPort, sendSock);
		this.rec = new Receiver(IP, inPort, receiveSock);
	}

}