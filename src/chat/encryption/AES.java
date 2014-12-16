/**
 * 
 */
package chat.encryption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Razvan
 * To do: generate random AES key!
 */
public class AES {
	String IV;
	String encryptionKey;
	SecureRandom random = new SecureRandom();
	SecretKeySpec key;

	AES(String providedIV, String providedEncryptionKey) throws Exception
	{
		if (providedIV.contentEquals(""))
			this.IV = "AAAAAAAAAAAAAAAA";
		if (providedEncryptionKey.contentEquals(""))
			this.encryptionKey = "0123456789abcdef";
		this.key = new SecretKeySpec(this.encryptionKey.getBytes("UTF-8"), "AES");
	}

	public byte[] encrypt(byte[] input) throws Exception 
	{
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
		return cipher.doFinal(input);
	}

	public byte[] decrypt(byte[] input) throws Exception
	{
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
		return cipher.doFinal(input);
	}

	public void generateKey() throws Exception
	{
		byte bytes[] = new byte[16];
		random.nextBytes(bytes);
		this.encryptionKey = new String(bytes, "UTF-8");
		this.encryptionKey = Arrays.toString(bytes);
		this.key = new SecretKeySpec(this.encryptionKey.getBytes("UTF-8"), "AES");
		
	}
	public void generateSalt() 
	{
		byte bytes[] = new byte[16];
		random.nextBytes(bytes);
		IV = new String(bytes);
		System.out.println(this.IV);
	}

}
