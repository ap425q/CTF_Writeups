package com.example.bombastic;

import android.util.Base64;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes3.dex */
public class AESUtils {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    public static String decrypt(String encryptedText, String key) throws Exception {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(2, secretKey);
        byte[] decodedValue = Base64.decode(encryptedText, 0);
        byte[] decryptedValue = cipher.doFinal(decodedValue);
        return new String(decryptedValue, StandardCharsets.UTF_8);
    }
}