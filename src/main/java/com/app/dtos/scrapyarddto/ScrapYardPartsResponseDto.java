package com.app.dtos.scrapyarddto;

import java.util.List;

import com.app.entity.Car;
import com.app.entity.ScrapYard;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScrapYardPartsResponseDto {

	private String partName;
	private Long scrapYardId;
	private List<Car> cars;
	private String scrapYardName;
	private String scrapYardLocation;
	private float price;
	private int wearLevel;
	private String category;
	private boolean reserved;
}
