/**
 * 
 */
package chat.communication;

/**
 * @author IulianC
 *
 */

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Speaker extends Thread {

	private static final String IP_TO_STREAM_TO = "127.0.0.1";
	private static final int PORT_TO_STREAM_TO = 9000;

	private static DatagramSocket sock;

	/**
	 * Creates a new instance of RadioReceiver
	 * 
	 * @throws SocketException
	 */
	public Speaker() throws SocketException {
		sock = new DatagramSocket(PORT_TO_STREAM_TO);
	}

	public void run() {
		byte b[] = null;
		while (true) {
			b = receiveThruUDP();
			toSpeaker(b);
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 * @throws SocketException
	 */
	public static void main(String[] args) throws SocketException {

		Speaker r = new Speaker();
		r.start();

	}

	public static byte[] receiveThruUDP() {
		try {

			byte soundpacket[] = new byte[8192];
			DatagramPacket datagram = new DatagramPacket(soundpacket,
					soundpacket.length, InetAddress.getByName(IP_TO_STREAM_TO),
					PORT_TO_STREAM_TO);
			sock.receive(datagram);
			// sock.close();
			return soundpacket;
		} catch (Exception e) {
			System.out.println(" Unable to send soundpacket using UDP ");
			return null;
		}

	}

	public static void toSpeaker(byte soundbytes[]) {

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
			System.out.println("not working in speakers ");
		}

	}

	public static AudioFormat getAudioFormat() {
		float sampleRate = 8000.0F;
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

}