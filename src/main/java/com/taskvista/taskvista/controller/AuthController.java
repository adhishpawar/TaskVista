package com.taskvista.taskvista.controller;


import com.taskvista.taskvista.auth.JwtUtil;
import com.taskvista.taskvista.dto.LoginRequest;
import com.taskvista.taskvista.dto.SignupRequest;
import com.taskvista.taskvista.entity.AppUser;
import com.taskvista.taskvista.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

@RequestMapping("api/auth")
@RestController
public class AuthController {

    @Autowired
    private AppUserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        AppUser user = new AppUser();
        user.setEmail(request.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setTenantId(request.getTenantId());

        userRepo.save(user);
        return ResponseEntity.ok("User created");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        AppUser user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        String accessToken = jwtUtil.generateAccessToken(user.getEmail(), user.getRole().name(), user.getTenantId());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());

        return ResponseEntity.ok(Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        ));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> payload){
        String refreshToken = payload.get("refreshToken");
        try{
            String username = jwtUtil.extractUsername(refreshToken);


            //verify if user still exists or token is valid in DB/session
            AppUser user  = userRepo.findByEmail(username).orElseThrow();
            if(!jwtUtil.isTokenValid(refreshToken)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh Token");
            }

            String newAccessToken = jwtUtil.generateAccessToken(user.getEmail(),user.getRole().name(), user.getTenantId());
            return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired refresh token");
        }
    }
}
