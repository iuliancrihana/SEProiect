package chat.communication;

/**
 * 
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Port;
import javax.sound.sampled.TargetDataLine;

/**
 * @author IulianC
 *
 */
public class Microphone implements Runnable {
	static volatile boolean keepMicOpen;
	volatile boolean isReader;
	volatile int counterRead = 0;
	volatile int counterSent = 0;

	Thread readThread, sendThread;
	private String IP_TO_STREAM_TO;// = "192.168.1.104";
	private int PORT_TO_STREAM_TO;// = 9000;
	private DatagramSocket sendSock;

	private DataLine.Info dataLineInfo;
	private TargetDataLine targetDataLine;
	private volatile byte tempBuffer[];
	private volatile byte roundRobinReadBuffer[][];

	/**
	 * Creates a new instance of MicPlayer
	 * 
	 * @throws LineUnavailableException
	 */
	public Microphone(String IP, int port, DatagramSocket sendSocket) {
		IP_TO_STREAM_TO = IP;
		PORT_TO_STREAM_TO = port;
		this.sendSock = sendSocket;
		init();
	}

	private void init() {
		this.dataLineInfo = new DataLine.Info(TargetDataLine.class,
				getAudioFormat());
		try {
			this.targetDataLine = (TargetDataLine) AudioSystem
					.getLine(dataLineInfo);
			this.targetDataLine.open(getAudioFormat());
		} catch (LineUnavailableException e) {
			System.out.println(e.getMessage());
			Microphone.keepMicOpen = false;
		}
		this.targetDataLine.start();
		this.tempBuffer = new byte[16000];
		this.roundRobinReadBuffer = new byte[100][16000];
		Microphone.keepMicOpen = true;
		this.isReader = true;

	}

	private static AudioFormat getAudioFormat() {
		float sampleRate = 11025.0F;
		// 8000,11025,16000,22050,44100
		int sampleSizeInBits = 16;
		// 8,16
		int channels = 1;
		// 1,2
		boolean signed = true;
		// true,false
		boolean bigEndian = false;
		// true,false
		return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed,
				bigEndian);
	}

	private void sendThruUDP(byte soundpacket[]) {
		try {
			sendSock.send(new DatagramPacket(soundpacket, soundpacket.length,
					InetAddress.getByName(IP_TO_STREAM_TO), PORT_TO_STREAM_TO));
		} catch (Exception e) {
			if (e.getMessage().equals("Socket is closed")) {
				System.out.println("S-a terminat conexiunea");
				Microphone.keepMicOpen = false;
			}
		}

	}

	@Override
	public void run() {
		System.out.println("entered run");
		if (this.isReader)
		{
			this.isReader = false;
			
			sendThread = new Thread(this);
			sendThread.start();
			
			readFromMic();
		}
		else
		{
			startSendingData();
		}

	}
	
	/**
	 * This function is called by the first thread readThread.
	 * It reads data from the microphone and stores it in a buffer to be sent by the second thread.
	 */
	public void readFromMic()
	{
		if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
			try {
				while (Microphone.keepMicOpen) {
					targetDataLine.read(tempBuffer, 0, tempBuffer.length);

					roundRobinReadBuffer[counterRead] = tempBuffer;
					System.out.println("read packet: "+ counterRead);
					counterRead++;
					counterRead = counterRead % 100;
				}

			} catch (Exception e) {
				System.out.println(" not correct ");
				System.exit(0);
			}
		}
	}
	
	
	/**
	 * This function is running in the 2nd thread.
	 * Before sending the data we should also encrypt it.
	 */
	public void startSendingData()
	{
		
		while (Microphone.keepMicOpen)
		{
			if (counterRead!=counterSent)
			{	
				System.out.println("Sent packet:" + counterSent);
				sendThruUDP(roundRobinReadBuffer[counterSent]);
				counterSent++;
				counterSent = counterSent % 100;
			}
		}
	}

	public static void setKeepMicOpen(boolean keepMicOpen) {
		Microphone.keepMicOpen = keepMicOpen;
	}

	public void start() {
		if (readThread == null) {
			readThread = new Thread(this);
			readThread.start();
		}		
	}

}
