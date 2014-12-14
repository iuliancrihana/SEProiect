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
public class Receiver {
	private final Thread t1;/* , t2; */

	private volatile boolean x;
	private byte b[] = null;

	private String IP_TO_STREAM_TO;// = "127.0.0.1";
	private int PORT_TO_STREAM_TO;// = 9005;

	private byte soundpacket[];
	private DatagramSocket receiveSock;
	private DatagramPacket datagram;

	public Receiver(String IP, int port, DatagramSocket receiveSocket) {
		IP_TO_STREAM_TO = IP;
		PORT_TO_STREAM_TO = port;
		this.receiveSock = receiveSocket;
		t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (x) {
					b = receiveThruUDP();
					toSpeaker(b);
				}
				System.out.println("Receptorul s-a terminat");
			}
		});
			init();
		/*
		 * t2 = new Thread(new Runnable() {
		 * 
		 * @Override public void run() { while (x) {
		 * System.out.println("thread2 - receiver");
		 * 
		 * } System.ou
		 * .println("Transmiterea de date catre speaker s-a terminat"); } });
		 */
	}

	private void init(){
		this.soundpacket = new byte[16000];
		try {
			this.datagram = new DatagramPacket(soundpacket, soundpacket.length,
					InetAddress.getByName(IP_TO_STREAM_TO), PORT_TO_STREAM_TO);
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}
		x = true;
	}

	private byte[] receiveThruUDP() {
		try {

			receiveSock.receive(datagram);
			return soundpacket;
		} catch (Exception e) {
			if (e.getMessage().equals("Socket closed")) {
				x = false;
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
				x = false;
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

	public void start() {
		try {
			t1.start();
			// t2.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
