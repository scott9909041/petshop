package org.pet.shop.service;

import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    public static String encrypt(String data) {
        //TODO: encrypt protocol should be decided.
        String encryptData = "encrypt_" + data;
        return encryptData;
    }

    public static String decrypt(String data) {
        //TODO: decrypt protocol should be decided.
        String decryptData = data.replace("encrypt_", "0");
        return decryptData;
    }
}
