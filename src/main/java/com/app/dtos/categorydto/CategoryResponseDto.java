package com.app.dtos.categorydto;

import com.app.dtos.scrapyarddto.ScrapYardPartsResponseDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryResponseDto {
	private String categoryId;
	private String categoryName;

}
