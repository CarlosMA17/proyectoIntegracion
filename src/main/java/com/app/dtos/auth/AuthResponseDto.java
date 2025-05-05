package com.app.dtos.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponseDto {

	private String accessToken;
	private String tokenType = "Bearer ";
	private Long scrapYardId;
	
	public AuthResponseDto(String accessToken, Long scrapYardId) {
		this.accessToken = accessToken;
		this.scrapYardId = scrapYardId;
	}
}
