package com.app.dtos.postdto;

import java.util.Date;
import java.util.Set;

import com.app.dtos.tagdto.TagResponseDto;
import com.app.dtos.writerdto.WriterResponseDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostResponseDto {

	private Long id;
	private String title;
	private String text;
	private Date createdOn;
	private Set<TagResponseDto> tags;
}
