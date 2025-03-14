package com.app.mappers.writer;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.accesodatos.entity.Writer;
import com.app.dtos.writerdto.WriterRequestDto;
import com.app.dtos.writerdto.WriterResponseDto;

@Mapper(componentModel = "spring")
public interface WriterMapper {

	@Mapping(target = "email", ignore = true)
	@Mapping(target = "posts", ignore = true)
	@Mapping(target = "id", ignore = true)
	Writer toEntity (WriterRequestDto writerRequestDto);
	
	WriterResponseDto toResponse(Writer writer);
}
