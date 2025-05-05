package com.app.service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.entity.Role;
import com.app.entity.UserEntity;
import com.app.dtos.auth.AuthLoginRequestDto;
import com.app.dtos.auth.AuthResponseDto;
import com.app.exception.ResourceNotFoundException;
import com.app.jwt.JwtTokenProvider;
import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	public Collection<GrantedAuthority> mapToAuthorities(Set<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName())))
																			.collect(Collectors.toList());
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("username-->" + username);
		
		UserEntity userEntity = userRepository.findUserEntityByUsername(username)
											  .orElseThrow(() -> new ResourceNotFoundException("Username: " + username + " not found!"));
		
		
		return new User(userEntity.getUsername(),
						userEntity.getPassword(),
						userEntity.isEnabled(),
						userEntity.isAccountNoExpired(),
						userEntity.isCredentialNoExpired(),
						userEntity.isAccountNoLocked(),
						mapToAuthorities(userEntity.getRoles())
						);
	}

	private Authentication authenticate(String username, String password) {
		System.out.println("authenticate -->" + username);
		UserDetails userDetails = this.loadUserByUsername(username);
		
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid username or password");
		}
		
		return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
	}
	
	public AuthResponseDto login(AuthLoginRequestDto authLoginRequest) {
		
		System.out.println("Auth--> " + authLoginRequest.getUsername());
		
		Authentication authentication = this.authenticate(authLoginRequest.getUsername(), authLoginRequest.getPassword());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String accessToken = jwtTokenProvider.generateToken(authentication);
		
		UserEntity user = userRepository.findUserEntityByUsername(authLoginRequest.getUsername())
			    .orElseThrow(() -> new UsernameNotFoundException("User not found"));		
		
		Long scrapYardId = null;
		if (user.getRoles().stream().anyMatch(r -> r.getName().equals("SCRAPYARD"))) {
		    scrapYardId = user.getScrapYard() != null ? user.getScrapYard().getScrapYardId() : null;
		}

		
		return new AuthResponseDto(accessToken, scrapYardId);
	}
	
	public AuthResponseDto register(AuthLoginRequestDto registerDto) {
	    if (userRepository.findUserEntityByUsername(registerDto.getUsername()).isPresent()) {
	        throw new RuntimeException("Username is already taken");
	    }
	    
	    Role roleUser = roleRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Role USER not found"));

	    UserEntity newUser = new UserEntity();
	    newUser.setUsername(registerDto.getUsername());
	    newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
	    newUser.setEnabled(true);
	    newUser.setAccountNoExpired(true);
	    newUser.setAccountNoLocked(true);
	    newUser.setCredentialNoExpired(true);
		newUser.setRoles(Set.of(roleUser));

	    userRepository.save(newUser);

	    // Autenticar directamente al usuario recién registrado
	    Authentication authentication = authenticate(registerDto.getUsername(), registerDto.getPassword());
	    SecurityContextHolder.getContext().setAuthentication(authentication);

	    String token = jwtTokenProvider.generateToken(authentication);

	    return new AuthResponseDto(token, null);
	}
	
}
