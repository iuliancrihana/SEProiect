package chat.encryption;
import java.io.UnsupportedEncodingException;

import chat.encryption.*;

public class MainRzv {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String example = "0123";
		byte[] bytes;
		try {
			bytes = example.getBytes();
			System.out.write(bytes);
			
			AES myAes = new AES("", "");
			byte[] encrypt = myAes.encrypt(bytes);
			byte[] decrypt = myAes.decrypt(encrypt);
			
			String returned = new String(decrypt, "UTF-8");
			System.out.println("\n\n" + returned);
			
			if (example.contentEquals(returned))
				System.out.println("egale");
			else
				System.out.println("diferite");
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
