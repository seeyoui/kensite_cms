package com.seeyoui.kensite.license.license;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

public class RSACoder {
	public static final String KEY_ALGORITHM = "RSA";
	private static final int _$3 = 512;
	private static final String _$2 = "RSAPublicKey";
	private static final String _$1 = "RSAPrivateKey";

	public static Map<String, Object> initKey() throws Exception {
		KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
		localKeyPairGenerator.initialize(512);
		KeyPair localKeyPair = localKeyPairGenerator.generateKeyPair();
		RSAPublicKey localRSAPublicKey = (RSAPublicKey) localKeyPair.getPublic();
		RSAPrivateKey localRSAPrivateKey = (RSAPrivateKey) localKeyPair.getPrivate();
		HashMap localHashMap = new HashMap();
		localHashMap.put("RSAPublicKey", localRSAPublicKey);
		localHashMap.put("RSAPrivateKey", localRSAPrivateKey);
		return localHashMap;
	}

	public static byte[] encryptByPrivateKey(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) throws Exception {
		PKCS8EncodedKeySpec localPKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(paramArrayOfByte2);
		KeyFactory localKeyFactory = KeyFactory.getInstance("RSA");
		PrivateKey localPrivateKey = localKeyFactory.generatePrivate(localPKCS8EncodedKeySpec);
		Cipher localCipher = Cipher.getInstance(localKeyFactory.getAlgorithm());
		localCipher.init(1, localPrivateKey);
		return localCipher.doFinal(paramArrayOfByte1);
	}

	public static byte[] encryptByPublicKey(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) throws Exception {
		KeyFactory localKeyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec localX509EncodedKeySpec = new X509EncodedKeySpec(paramArrayOfByte2);
		PublicKey localPublicKey = localKeyFactory.generatePublic(localX509EncodedKeySpec);
		Cipher localCipher = Cipher.getInstance(localKeyFactory.getAlgorithm());
		localCipher.init(1, localPublicKey);
		return localCipher.doFinal(paramArrayOfByte1);
	}

	public static byte[] decryptByPrivateKey(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) throws Exception {
		PKCS8EncodedKeySpec localPKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(paramArrayOfByte2);
		KeyFactory localKeyFactory = KeyFactory.getInstance("RSA");
		PrivateKey localPrivateKey = localKeyFactory.generatePrivate(localPKCS8EncodedKeySpec);
		Cipher localCipher = Cipher.getInstance(localKeyFactory.getAlgorithm());
		localCipher.init(2, localPrivateKey);
		return localCipher.doFinal(paramArrayOfByte1);
	}

	public static byte[] decryptByPublicKey(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) throws Exception {
		KeyFactory localKeyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec localX509EncodedKeySpec = new X509EncodedKeySpec(paramArrayOfByte2);
		PublicKey localPublicKey = localKeyFactory.generatePublic(localX509EncodedKeySpec);
		Cipher localCipher = Cipher.getInstance(localKeyFactory.getAlgorithm());
		localCipher.init(2, localPublicKey);
		return localCipher.doFinal(paramArrayOfByte1);
	}

	public static byte[] getPrivateKey(Map<String, Object> paramMap) {
		Key localKey = (Key) paramMap.get("RSAPrivateKey");
		return localKey.getEncoded();
	}

	public static byte[] getPublicKey(Map<String, Object> paramMap) throws Exception {
		Key localKey = (Key) paramMap.get("RSAPublicKey");
		return localKey.getEncoded();
	}
}