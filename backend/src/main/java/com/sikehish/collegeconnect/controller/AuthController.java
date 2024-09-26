package com.sikehish.collegeconnect.controller;
import com.sikehish.collegeconnect.model.WebUser;
import com.sikehish.collegeconnect.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private WebUserService webUserService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody WebUser webUser) {
        try {
            String token = webUserService.login(webUser.getUsername(), webUser.getPassword(), webUser.getRole().name());

            // Prepare response
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("username", webUser.getUsername());
            response.put("role", webUser.getRole().name());

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody WebUser user) {
        try {
            WebUser savedUser = webUserService.signup(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("test")
    public ResponseEntity<Map<String, String>> testEndpoint() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello World");
        return ResponseEntity.ok(response);
    }
}
