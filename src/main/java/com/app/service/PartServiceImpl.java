package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dtos.partdto.PartResponseDto;
import com.app.entity.Part;
import com.app.mappers.part.PartMapper;
import com.app.repository.PartRepository;

@Service
public class PartServiceImpl implements PartService {
	
	@Autowired PartRepository partRepository;
	@Autowired PartMapper partMapper;

	@Override
	public List<PartResponseDto> getAllPartsByCar(Long carId, Long categoryId) {
		List<Part> parts =  partRepository.findPartsByCarIdAndCategoryId(carId, categoryId);
		return parts.stream()
								.map(partMapper::toResponse)
								.collect(Collectors.toList());
	}
	
	@Override
	public List<PartResponseDto> getAllParts() {
		List<Part> parts =  partRepository.findAll();
		return parts.stream()
								.map(partMapper::toResponse)
								.collect(Collectors.toList());
	}
}
