package chat.communication;

/**
 * 
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.sound.sampled.TargetDataLine;

/**
 * @author IulianC
 *
 */
public class Microphone implements Runnable {
	Thread t;
	private String IP_TO_STREAM_TO;// = "192.168.1.104";
	private int PORT_TO_STREAM_TO;// = 9000;
	private static DatagramSocket sock;

	/**
	 * Creates a new instance of MicPlayer
	 * 
	 * @throws SocketException
	 */
	public Microphone(String IP, int port) throws SocketException {
		IP_TO_STREAM_TO = IP;
		PORT_TO_STREAM_TO = port;
		sock = new DatagramSocket();
	}

	/**
	 * @param args
	 *            the command line arguments
	 * @throws SocketException
	 */

	private static AudioFormat getAudioFormat() {
		float sampleRate = 16000.0F;
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
			// DatagramSocket sock = new DatagramSocket();
			sock.send(new DatagramPacket(soundpacket, soundpacket.length,
					InetAddress.getByName(IP_TO_STREAM_TO), PORT_TO_STREAM_TO));
			// sock.close();
		} catch (Exception e) {
			System.out.println(" Unable to send soundpacket using UDP ");
		}

	}

	@Override
	public void run() {
		Mixer.Info minfo[] = AudioSystem.getMixerInfo();
		for (int i = 0; i < minfo.length; i++) {
			System.out.println(minfo[i]);
		}

		if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
			try {

				DataLine.Info dataLineInfo = new DataLine.Info(
						TargetDataLine.class, getAudioFormat());
				TargetDataLine targetDataLine = (TargetDataLine) AudioSystem
						.getLine(dataLineInfo);
				targetDataLine.open(getAudioFormat());
				targetDataLine.start();
				byte tempBuffer[] = new byte[8192];

				while (true) {
					targetDataLine.read(tempBuffer, 0, tempBuffer.length);
					// byte[] encrypt = Aes.encrypt(tempBuffer);
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
