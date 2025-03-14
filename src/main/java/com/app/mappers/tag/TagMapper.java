package com.app.mappers.tag;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.accesodatos.entity.Tag;
import com.app.dtos.tagdto.TagRequestDto;
import com.app.dtos.tagdto.TagResponseDto;
import com.app.dtos.tagdto.TagResponseDtoPosts;


@Mapper(componentModel = "spring")
public interface TagMapper {
	
	//@Mapping(target = "studentId", ignore = true)
	//@Mapping(target = "courses", ignore = true)
	Tag toEntity (TagRequestDto tagRequestDto);
	
	TagResponseDto toResponse(Tag tag);
	TagResponseDtoPosts toResponsePosts(Tag tag);
}
