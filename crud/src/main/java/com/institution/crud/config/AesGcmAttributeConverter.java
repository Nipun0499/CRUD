package com.institution.crud.config;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Converter
public class AesGcmAttributeConverter implements AttributeConverter<String, String> {

    private SecretKey secretKey;
    private Cipher cipher;

    @Value("${aes.encryption.key}")
    String encryptionKey;

    @Override
    public String convertToDatabaseColumn(String s) {
        if (s == null) {
            return null;
        }
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] cipherText = cipher.doFinal(s.getBytes(StandardCharsets.UTF_8));
            byte[] iv = cipher.getIV();
            return Base64.getEncoder().encodeToString(iv) + ":" + Base64.getEncoder().encodeToString(cipherText);
        } catch (Exception e) {
            throw new RuntimeException("Error Encrypting attribute", e);
        }
    }

    @Override
    public String convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        try {
            String[] parts = s.split(":");
            byte[] iv = Base64.getDecoder().decode(parts[0]);
            byte[] cipherText = Base64.getDecoder().decode(parts[1]);

            GCMParameterSpec gcmSpec = new GCMParameterSpec(128, iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmSpec);

            byte[] plainTextBytes = cipher.doFinal(cipherText);
            return new String(plainTextBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting attribute", e);
        }
    }

    @PostConstruct
    public void getEncryptionKey() {
        try {
            System.out.println(encryptionKey);
//            String encryptionKey = objectMapper.readTree(secret).get("ATSEncryptionKey").asText();
            cipher = Cipher.getInstance("AES/GCM/NoPadding");
            secretKey = new SecretKeySpec(hexToBytes(encryptionKey), "AES");
        } catch (Exception e) {
            log.error("Exception ", e);
        }
    }

    private byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return bytes;
    }
}
