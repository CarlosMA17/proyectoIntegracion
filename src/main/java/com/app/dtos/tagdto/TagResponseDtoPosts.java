package com.app.dtos.tagdto;

import java.util.List;

import com.app.dtos.postdto.PostResponseDtoWriters;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TagResponseDtoPosts {
		private Long id;
		private String name;
		private List<PostResponseDtoWriters> posts;

}
