package com.example.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Authot: Albert Akimov
 * @Date: 22.10.2021
 * @Description:
 */

public class SHAUtil {

    public static String hashPassword(String password) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert md != null;
        byte[] digest = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return sb.toString();
    }
}
