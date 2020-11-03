package com.xbcxs.fileupload.common;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Singleton
 *
 * @author x
 */
public class CipherKey {

    private static final String SECRET_KEY = "fileupload";
    /**
     * DES运算速度较快,安全性低,资源消耗中; AES运算速度快,安全性高,资源消耗低
     */
    private static final String ALGORITHM = "AES";

    public static Cipher getEncryptCipher() {
        return getCipher(Cipher.ENCRYPT_MODE, ALGORITHM, SECRET_KEY);
    }

    public static Cipher getDecryptCipher() {
        return getCipher(Cipher.DECRYPT_MODE, ALGORITHM, SECRET_KEY);
    }

    private static Cipher getCipher(int crypt, String algorithm, String secretKey) {
        Cipher cipher = null;
        try {
            KeyGenerator generator = KeyGenerator.getInstance(algorithm);
            generator.init(new SecureRandom(secretKey.getBytes()));
            cipher = Cipher.getInstance(algorithm);
            cipher.init(crypt, generator.generateKey());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithm [" + algorithm + "]: " + e);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return cipher;
    }


}
