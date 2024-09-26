package com.sikehish.collegeconnect.service;
import com.sikehish.collegeconnect.config.JwtUtil;
import com.sikehish.collegeconnect.model.WebUser;
import com.sikehish.collegeconnect.repository.WebUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class WebUserService {

    @Autowired
    private WebUserRepository webUserRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String username, String password, String role) {
        WebUser user = webUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        if (!user.getRole().name().equals(role)) {
            throw new RuntimeException("Role mismatch");
        }

        return jwtUtil.generateToken(username, user.getRole().name());
    }


    public WebUser signup(WebUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return webUserRepository.save(user);
    }
}
