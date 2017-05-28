package net.cypher.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class CypherSecurity {
	
		
	/**
	     * Encrypts plainText in AES using the secret key
	     * @param plainText
	     * @param secKey
	     * @return
	     * @throws Exception
	     */
	    public String encryptText(String plainText, SecretKey secKey) throws Exception{
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
	        public String decryptText(String byteCipherText, SecretKey secKey) throws Exception {
	            // AES defaults to AES/ECB/PKCS5Padding in Java 7
	            Cipher aesCipher = Cipher.getInstance("AES");
	            aesCipher.init(Cipher.DECRYPT_MODE, secKey);
	            byte[] bytePlainText = aesCipher.doFinal(Base64.decodeBase64(byteCipherText));
	            return new String(bytePlainText, "UTF-8");
	        }
	
	


	public String decrypt(Data myData) throws NoSuchAlgorithmException, NoSuchPaddingException, Exception,
			InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		
		String decryptedKey = this.decryptText(myData.key, this.getKey());
		System.out.println("decryptedKey : "+decryptedKey);
		
		// decode the base64 encoded string
		byte[] decodedKey = Base64.decodeBase64(decryptedKey);
		// rebuild key using SecretKeySpec
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); 

		String decryptedData = decryptText(myData.data, originalKey);
		return decryptedData;
	}


	Key key;
	
	public Key getKey() {
		return key;
	}


	public void setKey(Key key) {
		this.key = key;
	}


	public Data encrypt(String data) throws NoSuchAlgorithmException, Exception, NoSuchPaddingException,
			UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		Data myData = new Data();
		myData.data = data;
		
		SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
		String encryptedData = encryptText(myData.data, secretKey);
		System.out.println("encryptedData : "+encryptedData);
		
		// get base64 encoded version of the key
		String encodedKey = Base64.encodeBase64String(secretKey.getEncoded());
		System.out.println("encodedKey : "+encodedKey);
			
		
		String encodedKey1 = this.encryptText(encodedKey, this.getKey());
		System.out.println("encodedKey1 : "+encodedKey1);
		
		myData.key = encodedKey1;
		myData.data = encryptedData;
		return myData;
	}
	
	public String encryptText(String msg, Key key)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			UnsupportedEncodingException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeyException {
		this.cipher.init(Cipher.ENCRYPT_MODE, key);
		return Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
	}

	public String decryptText(String msg, Key key)
			throws InvalidKeyException, UnsupportedEncodingException,
			IllegalBlockSizeException, BadPaddingException {
		this.cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(Base64.decodeBase64(msg)), "UTF-8");
	}
	
	private Cipher cipher;

	public CypherSecurity(Key key) throws NoSuchAlgorithmException, NoSuchPaddingException {
		this.cipher = Cipher.getInstance("RSA");
		this.key = key;
	}
}

class Data {
	String data;
	String key;
}