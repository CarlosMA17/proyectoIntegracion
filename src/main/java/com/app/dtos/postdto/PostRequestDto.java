package com.app.dtos.postdto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostRequestDto {
	private String title;
	private String text;
	private Date createdOn;
}
