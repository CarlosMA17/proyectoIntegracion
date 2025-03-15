package com.app.dtos.bookdto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScrapYardPartsResponseDto {

	private String partName;
	private String carBrand;
	private String carModel;
	private String carEngine;
	private String scrapYardName;
	private String scrapYardLocation;
	private float price;
	private int wearLevel;
	private String category;
}
