package com.app.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accesodatos.entity.Writer;
import com.app.dtos.writerdto.WriterResponseDto;
import com.app.exception.ResourceNotFoundException;
import com.app.mappers.writer.WriterMapper;
import com.app.repository.WriterRepository;

@Service
public class WriterServiceImpl implements WriterService {
	
	private final static String WRITER_NOT_FOUND = "writer with id %d not found";

	
	@Autowired WriterRepository writerRepo;
	@Autowired WriterMapper writerMapper;
	
	private Writer validateAndGetWriter(Long id) {
		return writerRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format(WRITER_NOT_FOUND, id)));
	}
	
	@Override
	public WriterResponseDto getWriterById(Long writerId) {
		Writer writer = validateAndGetWriter(writerId);
		
		
		return writerMapper.toResponse(writer);
	}
}
