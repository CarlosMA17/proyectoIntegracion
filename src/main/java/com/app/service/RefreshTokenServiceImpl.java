package com.app.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.entity.RefreshToken;
import com.app.repository.RefreshTokenRepository;
import com.app.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

	@Value("${jwt.refresh.expirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public RefreshToken createRefreshToken(Long userId) {
        // Elimina token anterior si existe
        refreshTokenRepository.deleteByUser_UserId(userId);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userRepository.findById(userId).orElseThrow());
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token ha expirado.");
        }
        return token;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(
            userRepository.findById(userId).get());
    }
    
    @Transactional
    public void deleteByToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
