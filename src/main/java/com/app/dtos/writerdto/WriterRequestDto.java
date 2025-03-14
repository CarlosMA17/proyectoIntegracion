package com.app.dtos.writerdto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WriterRequestDto {
	private String name;
	private String email;
}
