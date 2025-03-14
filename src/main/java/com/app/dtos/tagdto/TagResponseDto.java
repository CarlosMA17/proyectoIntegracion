package com.app.dtos.tagdto;


import java.util.List;

import com.app.dtos.postdto.PostResponseDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TagResponseDto {
	private Long id;
	private String name;
}
