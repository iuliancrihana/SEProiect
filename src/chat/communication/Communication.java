package chat.communication;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Communication {
	private final Microphone mic;
	private final Receiver rec;

	private DatagramSocket sendSock;
	/**
	 * @return the sendSock
	 */

	private DatagramSocket receiveSock;

	public DatagramSocket getSendSock() {
		return sendSock;
	}

	/**
	 * @return the receiveSock
	 */
	public DatagramSocket getReceiveSock() {
		return receiveSock;
	}

	 public static void main(String[] args) throws SocketException {

	 Communication c = new Communication("localhost", 5054, 5054);
	 c.mic.start();
	 c.rec.start();

	// daca inchid socketul...o sa stiu ca s-a terminat conexiunea
	// c.sendSock.close();
	// c.receiveSock.close();
	 }

	public void startCommunication() {
		this.mic.start();
		this.rec.start();
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
		sendSock = new DatagramSocket();
		receiveSock = new DatagramSocket(inPort);
		this.mic = new Microphone(IP, outPort, sendSock);
		this.rec = new Receiver(IP, inPort, receiveSock);
	}

}