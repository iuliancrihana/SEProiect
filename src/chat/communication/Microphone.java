package chat.communication;

/**
 * 
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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
	volatile boolean x;

	Thread t;
	private String IP_TO_STREAM_TO;// = "192.168.1.104";
	private int PORT_TO_STREAM_TO;// = 9000;
	private DatagramSocket sendSock;

	private DataLine.Info dataLineInfo;
	private TargetDataLine targetDataLine;
	private byte tempBuffer[];

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
		}
		this.targetDataLine.start();
		this.tempBuffer = new byte[16000];
		x = true;
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
				x = false;
				System.out.println("S-a terminat conexiunea");
			}
		}

	}

	@Override
	public void run() {
		if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
			try {
				while (x) {
					targetDataLine.read(tempBuffer, 0, tempBuffer.length);
					sendThruUDP(tempBuffer);

				}

			} catch (Exception e) {
				System.out.println(" not correct ");
				System.exit(0);
			}
		}

	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

}
