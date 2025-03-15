package com.app.dtos.bookdto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScrapYardPartsResponseDto {

	private String partName;
	private String scrapYardName;
	private float price;
	private boolean stock;
	private int wearLevel;
	//private String carName;
}
