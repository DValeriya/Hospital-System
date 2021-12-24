package com.teama.hospitalsystem.controllers;

import com.teama.hospitalsystem.request.LoginRequest;
import com.teama.hospitalsystem.util.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

@Validated
@RestController
@RequestMapping("/api/authorization")
public class AuthorizationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthorizationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getLogin().toString(),
                                loginRequest.getPassword()));
        UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new HashMap<String, String>() {{
            put("token", jwtToken);
        }});
    }
}
