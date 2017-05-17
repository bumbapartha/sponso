package net.cypher.security;

import java.io.File;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import org.apache.commons.codec.binary.Base64;
import java.util.Date;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class CypherPrivate {
	private static Random rand = new Random((new Date()).getTime());
	
	
	public static String salt(){
		BASE64Encoder encoder = new BASE64Encoder();
		byte[] salt = new byte[32];
		rand.nextBytes(salt);
		return encoder.encode(salt);
	}
	
	/**
	     * Encrypts plainText in AES using the secret key
	     * @param plainText
	     * @param secKey
	     * @return
	     * @throws Exception
	     */
	    public static String encryptText(String plainText,SecretKey secKey) throws Exception{
	        // AES defaults to AES/ECB/PKCS5Padding in Java 7
	        Cipher aesCipher = Cipher.getInstance("AES");
	        aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
	        byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes("UTF-8"));
	        return Base64.encodeBase64String(byteCipherText);
	    }
	    
	    
	    /**
	         * Decrypts encrypted byte array using the key used for encryption.
	         * @param byteCipherText
	         * @param secKey
	         * @return
	         * @throws Exception
	         */
	        public static String decryptText(String byteCipherText, SecretKey secKey) throws Exception {
	            // AES defaults to AES/ECB/PKCS5Padding in Java 7
	            Cipher aesCipher = Cipher.getInstance("AES");
	            aesCipher.init(Cipher.DECRYPT_MODE, secKey);
	            byte[] bytePlainText = aesCipher.doFinal(Base64.decodeBase64(byteCipherText));
	            return new String(bytePlainText, "UTF-8");
	        }
	
	public static void main(String[] args) throws Exception {
		
		String data = "This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data"
				+"This could be a very very big data";
		
		SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
		String encryptedData = encryptText(data, secretKey);
		System.out.println("encryptedData : "+encryptedData);
		
		// get base64 encoded version of the key
		String encodedKey = Base64.encodeBase64String(secretKey.getEncoded());
		System.out.println("encodedKey : "+encodedKey);
			
		AsymmetricCryptography ac = new AsymmetricCryptography();
		PrivateKey privateKey = ac.getPrivate("KeyPair/privateKey");
		PublicKey publicKey = ac.getPublic("KeyPair/publicKey");
		
		
		String encodedKey1 = ac.encryptText(encodedKey, privateKey);
		System.out.println("encodedKey1 : "+encodedKey1);
		
		String decryptedKey = ac.decryptText(encodedKey1, publicKey);
		System.out.println("decryptedKey : "+decryptedKey);
		
		// decode the base64 encoded string
		byte[] decodedKey = Base64.decodeBase64(decryptedKey);
		// rebuild key using SecretKeySpec
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); 

		String decryptedData = decryptText(encryptedData, originalKey);
		System.out.println("decryptedData:"+decryptedData);
	}
}
