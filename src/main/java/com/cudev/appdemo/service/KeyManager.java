package com.cudev.appdemo.service;

import com.cudev.appdemo.util.RSAUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.PublicKey;

import java.util.Map;

@Service
public class KeyManager {
    private PublicKey publicKey;

    @PostConstruct
    public void init() {
        fetchPublicKeyFromAuthService();
    }

    @Scheduled(fixedDelay = 3600000 ) // mỗi 1 tiếng refresh key 1 lần
    public void refreshKey() {
        fetchPublicKeyFromAuthService();
    }

    private void fetchPublicKeyFromAuthService() {
        // Giả sử dùng RestTemplate, hoặc WebClient nếu reactive
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity("http://localhost:8080/key-manager/get-public-key", Map.class);

        // Kiểm tra nếu response có giá trị publicKey
        if (response.getBody() != null && response.getBody().containsKey("publicKey")) {

            try {
                String keyString = (String) response.getBody().get("publicKey");

                this.publicKey = RSAUtil.decodePublicKey(keyString);
            } catch (Exception e) {
                throw new RuntimeException("Error decoding public key", e);
            }

        }
    }

    public PublicKey getPublicKey() {
        if (publicKey == null) {
            throw new IllegalStateException("Public key has not been initialized yet");
        }
        return publicKey;
    }
}
