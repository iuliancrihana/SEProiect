/**
 * 
 */
package chat.communication;

/**
 * @author IulianC
 *
 */
import javax.sound.sampled.*;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Microphone {

	private static final String IP_TO_STREAM_TO = "127.0.0.1";
	private static final int PORT_TO_STREAM_TO = 9000;

	private static DatagramSocket sock;

	/**
	 * Creates a new instance of MicPlayer
	 * 
	 * @throws SocketException
	 */
	public Microphone() throws SocketException {

	}

	/**
	 * @param args
	 *            the command line arguments
	 * @throws SocketException
	 */
	public static void main(String[] args) throws SocketException {
		sock = new DatagramSocket();
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

	public static void sendThruUDP(byte soundpacket[]) {
		try {
			// DatagramSocket sock = new DatagramSocket();
			sock.send(new DatagramPacket(soundpacket, soundpacket.length,
					InetAddress.getByName(IP_TO_STREAM_TO), PORT_TO_STREAM_TO));
			// sock.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Unable to send soundpacket using UDP ");
		}

	}

}