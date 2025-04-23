package com.cudev.appdemo.config;

import com.cudev.appdemo.base.ReponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@Component
public class AuthClient {
    @Autowired
    private RestTemplate restTemplate;

    public ReponseObject validateToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        System.out.println("Token: " + token);
        try {
            ResponseEntity<ReponseObject> response = restTemplate.exchange(
                    "http://localhost:8080/api/auth/validate",
                    HttpMethod.POST,
                    entity,
                    ReponseObject.class
            );
            if(response.getStatusCode() != HttpStatus.OK) {
                return new ReponseObject(false, "Token không hợp lệ", null);
            }
            ReponseObject responseBody = response.getBody();
            return responseBody;
        } catch (Exception e) {
            return new ReponseObject(false, "Token không hợp lệ", null);
        }
    }
}
