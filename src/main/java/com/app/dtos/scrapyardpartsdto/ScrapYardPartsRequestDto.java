package com.app.dtos.scrapyardpartsdto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScrapYardPartsRequestDto {
	private Long scrapyardPartId;
	private String partName;
	private Long carId;
	private int wearLevel;
	private float price;
	private boolean reserved;
}
