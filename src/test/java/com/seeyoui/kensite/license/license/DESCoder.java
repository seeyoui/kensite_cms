package com.seeyoui.kensite.license.license;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESCoder {
	public static final String KEY_ALGORITHM = "DES";
	public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

	public static byte[] initkey() throws Exception {
		KeyGenerator localKeyGenerator = KeyGenerator.getInstance("DES");
		localKeyGenerator.init(56);
		SecretKey localSecretKey = localKeyGenerator.generateKey();
		return localSecretKey.getEncoded();
	}

	public static Key toKey(byte[] paramArrayOfByte) throws Exception {
		DESKeySpec localDESKeySpec = new DESKeySpec(paramArrayOfByte);
		SecretKeyFactory localSecretKeyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey localSecretKey = localSecretKeyFactory.generateSecret(localDESKeySpec);
		return localSecretKey;
	}

	public static byte[] encrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) throws Exception {
		Key localKey = toKey(paramArrayOfByte2);
		Cipher localCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		localCipher.init(1, localKey);
		return localCipher.doFinal(paramArrayOfByte1);
	}

	public static byte[] decrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) throws Exception {
		Key localKey = toKey(paramArrayOfByte2);
		Cipher localCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		localCipher.init(2, localKey);
		return localCipher.doFinal(paramArrayOfByte1);
	}
}