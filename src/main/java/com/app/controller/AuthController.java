package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.auth.AuthLoginRequestDto;
import com.app.dtos.auth.AuthResponseDto;
import com.app.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponseDto> login(@RequestBody AuthLoginRequestDto loginRequest){
		return new ResponseEntity<>(userDetailsService.login(loginRequest), HttpStatus.OK);
	}
}
