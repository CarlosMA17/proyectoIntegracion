package com.app.dtos.scrapyarddto;

import com.app.dtos.carsdtos.CarResponseDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScrapYardPartsResponseDto {

	private Long scrapYardPartId;
	private String partName;
	private Long partId;
	private Long scrapYardId;
	private CarResponseDto car;
	private String scrapYardName;
	private String scrapYardLocation;
	private float price;
	private int wearLevel;
	private String category;
	private boolean reserved;
}
