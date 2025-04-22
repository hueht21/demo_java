//package com.cudev.appdemo.controllers.authentication;
//
//
//import com.cudev.appdemo.base.ReponseObject;
//import com.cudev.appdemo.entity.User;
//import com.cudev.appdemo.model.response.LoginResponse;
//import com.cudev.appdemo.model.response.UserForLogin;
//import com.cudev.appdemo.service.AuthenticationService;
//import com.cudev.appdemo.service.JWTService;
//import com.cudev.appdemo.service.UserService;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Collections;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/auth")
//public class OAuthController {
//
//    @Autowired
//    private AuthenticationService authenticationService;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private JWTService jwtService;
//
//    @Value("${spring.security.oauth2.client.registration.google.client-id}")
//    private String googleClientId;
//
//
//    @PostMapping("/google")
//    public ResponseEntity<ReponseObject> googleLogin(@RequestBody Map<String, String> body) {
//        String idTokenString = body.get("token");
//
//        try {
//            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory()).setAudience(Collections.singletonList(googleClientId)).build();
//
//            GoogleIdToken idToken = verifier.verify(idTokenString);
//            if (idToken != null) {
//                GoogleIdToken.Payload payload = idToken.getPayload();
//
//                // Lấy thông tin người dùng từ token
//                String email = payload.getEmail();
//                String name = (String) payload.get("name");
//
//
//                User user = userService.getUser(email, name);
//
//
//                if(user.getStatusUser() == 1) {
//                    // Sinh JWT token
//                    String jwt = jwtService.generateToken(user.getUserName());
//
//                    UserForLogin userForLogin = authenticationService.toUserForLogin(user);
//
//
//                    LoginResponse loginResponse = new LoginResponse(jwt, userForLogin);
//
//                    // Trả về frontend
//                    return new ResponseEntity<ReponseObject>(new ReponseObject(true, "Login Successful", loginResponse), HttpStatus.OK);
//                }else {
//                    // Trả về frontend
//                    return new ResponseEntity<ReponseObject>(new ReponseObject(false, "Tài khoản đã bị khoá", null), HttpStatus.OK);
//                }
//
//            } else {
//                return new ResponseEntity<ReponseObject>(new ReponseObject(true, "Invalid ID token", null), HttpStatus.UNAUTHORIZED);
//            }
//
//        } catch (Exception e) {
//            return new ResponseEntity<ReponseObject>(new ReponseObject(true, "Login failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
