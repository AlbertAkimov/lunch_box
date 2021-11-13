package com.lunchbox.util.crypt;

import android.content.Context;
import android.util.Base64;

import com.lunchbox.util.PropertiesUtil;

import java.io.IOException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Authot: Albert Akimov
 * @Date: 22.10.2021
 * @Description:
 */

public class AESCrypt {

    private final String algorithm;
    private String key;

    public AESCrypt(Context context) {
        this.algorithm = "AES";
        try {
            this.key = PropertiesUtil.getProperty("aes.key", context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String value) throws Exception {

        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte [] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
        return Base64.encodeToString(encryptedByteValue, Base64.DEFAULT);

    }

    public String decrypt(String value) throws Exception {

        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedValue64 = Base64.decode(value, Base64.DEFAULT);
        byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
        return new String(decryptedByteValue,"utf-8");

    }

    private Key generateKey() throws Exception {
        return new SecretKeySpec(key.getBytes(), algorithm);
    }
}
