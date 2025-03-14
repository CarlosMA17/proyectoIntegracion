package com.app.dtos.writerdto;

import java.util.List;

import com.app.dtos.postdto.PostResponseDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WriterResponseDto {
	private Long id;
	private String name;
	private String email;
	private List<PostResponseDto> posts;
}
