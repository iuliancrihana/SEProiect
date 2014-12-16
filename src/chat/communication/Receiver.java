package chat.communication;

/**
 * 
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 * @author IulianC
 *
 */
public class Receiver implements Runnable{
	private Thread recieveThread, outputThread;

	static volatile boolean keepListening;
	private volatile boolean isReciever;
	volatile int counterRecieved = 0;
	volatile int counterPlayed = 0;
	private byte b[] = null;

	private String IP_TO_STREAM_TO;// = "127.0.0.1";
	private int PORT_TO_STREAM_TO;// = 9005;

	private volatile byte tempBuffer[];
	private volatile byte roundRobinReadBuffer[][];
	private DatagramSocket receiveSock;
	private DatagramPacket datagram;

	public Receiver(String IP, int port, DatagramSocket receiveSocket) {
		IP_TO_STREAM_TO = IP;
		PORT_TO_STREAM_TO = port;
		this.receiveSock = receiveSocket;
		
		init();
	}

	private void init()
	{
		this.tempBuffer = new byte[16000];
		this.roundRobinReadBuffer = new byte[100][16000];
		try {
			this.datagram = new DatagramPacket(tempBuffer, tempBuffer.length,
					InetAddress.getByName(IP_TO_STREAM_TO), PORT_TO_STREAM_TO);
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}
		Receiver.keepListening = true;
		this.isReciever = true;
	}

	private byte[] receiveThruUDP() {
		try {
			System.out.println("recieved");
			receiveSock.receive(datagram);
			return tempBuffer;
		} catch (Exception e) {
			if (e.getMessage().equals("Socket closed")) {
				Receiver.keepListening = false;
			} else
				System.out.println("error in receive ThruUDP");
			return null;
		}

	}

	private void toSpeaker(byte soundbytes[]) {

		try {
			DataLine.Info dataLineInfo = new DataLine.Info(
					SourceDataLine.class, getAudioFormat());
			SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem
					.getLine(dataLineInfo);
			sourceDataLine.open(getAudioFormat());
			sourceDataLine.start();

			sourceDataLine.write(soundbytes, 0, soundbytes.length);
			sourceDataLine.drain();
			sourceDataLine.close();
		} catch (Exception e) {
			if (e.getMessage().equals("Socket closed")) {
				Receiver.keepListening = false;
			} else
				System.out.println("error sending to speakers");
			System.out.println(e.getMessage());
		}

	}

	public static AudioFormat getAudioFormat() {
		float sampleRate = 11025.0F; // 8000,11025,16000,22050,44100
		int sampleSizeInBits = 16; // 8,16
		int channels = 1; // 1,2
		boolean signed = true; // true,false
		boolean bigEndian = false; // true,false
		return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed,
				bigEndian);
	}

	public void run()
	{
		if (isReciever)
		{
			isReciever = false;
			outputThread = new Thread(this);
			outputThread.start();
			
			recieveData();
		}
		else
		{
			playAudio();
		}
		
	}
	
	
	public void playAudio()
	{
		while (Receiver.keepListening) 
		{
			if (counterPlayed!=counterRecieved)
			{
				toSpeaker(this.roundRobinReadBuffer[this.counterPlayed]);
				counterPlayed++;
				counterPlayed = counterPlayed % 100;
			}
		}
		
	}
	
	public void recieveData()
	{
		while (Receiver.keepListening) {
			b = receiveThruUDP();
			if (b==null)
			{
				keepListening = false;
				return;
			}
			System.out.println("length of packet: "+ b.length);
			this.roundRobinReadBuffer[this.counterRecieved] = b;
			counterRecieved++;
			counterRecieved = counterRecieved % 100;
			//toSpeaker(b);
		}
	}
	
	public static void setKeepListening(boolean keepListening) {
		Receiver.keepListening = keepListening;
	}
	
	
	public void start() 
	{
		if (recieveThread == null) {
			recieveThread = new Thread(this);
			recieveThread.start();
		}
	}
}
