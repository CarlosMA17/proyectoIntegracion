package com.app.mappers.post;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.accesodatos.entity.Post;
import com.app.dtos.postdto.PostRequestDto;
import com.app.dtos.postdto.PostResponseDto;


@Mapper(componentModel = "spring")
public interface PostMapper {
	
	//@Mapping(target = "courseId", ignore = true)
	//@Mapping(target = "students", ignore = true)
	Post toEntity (PostRequestDto postRequestDto);
	
	PostResponseDto toResponse(Post post);
}
