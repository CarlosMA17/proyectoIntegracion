package com.app.service;

import com.app.dtos.writerdto.WriterResponseDto;

public interface WriterService {
	WriterResponseDto getWriterById(Long writerid);
}
