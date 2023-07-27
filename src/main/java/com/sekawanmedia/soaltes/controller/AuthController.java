package com.sekawanmedia.soaltes.controller;

import com.sekawanmedia.soaltes.config.AppProperties;
import com.sekawanmedia.soaltes.dto.LoginRequest;
import com.sekawanmedia.soaltes.dto.RegisterRequestDTO;
import com.sekawanmedia.soaltes.model.AppUser;
import com.sekawanmedia.soaltes.security.jwt.JwtUtils;
import com.sekawanmedia.soaltes.security.service.RefreshTokenService;
import com.sekawanmedia.soaltes.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private AppUserService appUserService;
    private JwtUtils jwtUtils;
    private RefreshTokenService refreshTokenService;
    private AppProperties appProperties;


    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequestDTO dto) {
        appUserService.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser userDetails = (AppUser) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        
        Map<String, Object> userResponse = new LinkedHashMap<>();
        userResponse.put("id", userDetails.getId());
        userResponse.put("username", userDetails.getUsername());
        userResponse.put("email", userDetails.getEmail());
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("token", jwt );
        responseBody.put("type", "Bearer");
        responseBody.put("user", userResponse );
        return ResponseEntity.ok(responseBody);

    }





}
