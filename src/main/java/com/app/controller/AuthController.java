package com.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.auth.AuthLoginRequestDto;
import com.app.dtos.auth.AuthResponseDto;
import com.app.dtos.auth.JwtResponseDto;
import com.app.dtos.auth.TokenRefreshRequestDto;
import com.app.entity.RefreshToken;
import com.app.entity.UserEntity;
import com.app.jwt.JwtTokenProvider;
import com.app.service.RefreshTokenServiceImpl;
import com.app.service.UserDetailsServiceImpl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RefreshTokenServiceImpl refreshTokenService;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@CookieValue(value = "refreshToken", required = false) String requestRefreshToken) {
        if (requestRefreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No se encontró el refresh token en la cookie.");
        }

        return refreshTokenService.findByToken(requestRefreshToken)
            .map(refreshTokenService::verifyExpiration)
            .map(RefreshToken::getUser)
            .map(user -> {
            	String newAccessToken = userDetailsService.refreshToken(user);

                return ResponseEntity.ok(new JwtResponseDto(newAccessToken, requestRefreshToken));
            })
            .orElseGet(() -> ResponseEntity
            	    .status(HttpStatus.FORBIDDEN)
            	    .body(new JwtResponseDto(null, null)));    
       }
	
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthLoginRequestDto loginRequest, HttpServletResponse response) {
        AuthResponseDto authResponse = userDetailsService.login(loginRequest);

        Cookie refreshCookie = new Cookie("refreshToken", authResponse.getRefreshToken());
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(false); // solo si estás en HTTPS
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(7 * 24 * 60 * 60); // 7 días

        response.addCookie(refreshCookie);

        // devolvés solo el accessToken y los datos públicos
        return ResponseEntity.ok(new AuthResponseDto(
            authResponse.getAccessToken(),
            null,
            authResponse.getScrapYardId(),
            authResponse.getUserId()
        ));
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response, @CookieValue(value = "refreshToken", required = false) String refreshToken) {
    	
    	System.out.println(refreshToken);
    	System.out.println(refreshToken);
    	System.out.println(refreshToken);
    	System.out.println(refreshToken);
    	System.out.println(refreshToken);
    	System.out.println(refreshToken);
    	System.out.println(refreshToken);
    	System.out.println(refreshToken);
        if (refreshToken != null) {
            refreshTokenService.deleteByToken(refreshToken);
        }

        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // borrar

        response.addCookie(cookie);
        return ResponseEntity.ok("Sesión cerrada correctamente.");
    }
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponseDto> register(@RequestBody AuthLoginRequestDto loginRequest,  HttpServletResponse response){
		AuthResponseDto authResponse = userDetailsService.register(loginRequest);
		
		Cookie refreshCookie = new Cookie("refreshToken", authResponse.getRefreshToken());
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(true); // solo si estás en HTTPS
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(7 * 24 * 60 * 60); // 7 días

        response.addCookie(refreshCookie);

        // devolvés solo el accessToken y los datos públicos
        return ResponseEntity.ok(new AuthResponseDto(
            authResponse.getAccessToken(),
            null,
            authResponse.getScrapYardId(),
            authResponse.getUserId()
        ));
	}
}
