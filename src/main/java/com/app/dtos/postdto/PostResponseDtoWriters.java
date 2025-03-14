package com.app.dtos.postdto;

import java.util.Date;

import com.app.dtos.writerdto.WriterReponseDtoNoPosts;
import com.app.dtos.writerdto.WriterResponseDto;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class PostResponseDtoWriters {
	private Long id;
	private String title;
	private String text;
	private Date createdOn;
	private WriterReponseDtoNoPosts writer;
}
