package com.alten;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import javax.crypto.SecretKey;

public class JwtSecretKeyGenerator {
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Generates a secure key
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Generated Secure Base64 Key: " + base64Key);
    }
}
