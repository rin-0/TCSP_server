package org.obsidian.tcsp.util.security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @Author Rin
 * @Date 2017/11/24
 */
public class Cryptor {
    private static SecretKey secretKey;
    private static Cipher encryptCipher,decryptCipher;

    private static final String ALGORITHM = "AES";
    private static final String PASSWORD = "thekeyofobsidian";
    static {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            SecureRandom random = new SecureRandom();
            random.setSeed(PASSWORD.getBytes());
            keyGenerator.init(random);
            secretKey = keyGenerator.generateKey();

            encryptCipher = Cipher.getInstance(ALGORITHM);
            encryptCipher.init(Cipher.ENCRYPT_MODE,secretKey);

            decryptCipher = Cipher.getInstance(ALGORITHM);
            decryptCipher.init(Cipher.DECRYPT_MODE,secretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized String encrypt(String content){
        String base64Result = null;
        try {
//            Cipher cipher = Cipher.getInstance(ALGORITHM);
//            cipher.init(Cipher.ENCRYPT_MODE,secretKey);
            byte[] result = encryptCipher.doFinal(content.getBytes());
            base64Result = Base64.getEncoder().encodeToString(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return base64Result;
    }

    public static synchronized String decrypt(String encryptedContent){
        String content = null;
        try {
//            Cipher cipher = Cipher.getInstance(ALGORITHM);
//            cipher.init(Cipher.DECRYPT_MODE,secretKey);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedContent.getBytes());
            byte[] result = decryptCipher.doFinal(encryptedBytes);
            content = new String(result);
        } catch (Exception e){
            e.printStackTrace();
        }
        return content;
    }
}
