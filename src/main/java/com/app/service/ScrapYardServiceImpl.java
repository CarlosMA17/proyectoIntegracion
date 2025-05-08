package com.app.service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dtos.partdto.PartResponseDto;
import com.app.dtos.scrapyarddto.ScrapYardPartsResponseDto;
import com.app.dtos.scrapyarddto.ScrapYardRequestDto;
import com.app.entity.Part;
import com.app.entity.ScrapYard;
import com.app.entity.ScrapYardParts;
import com.app.entity.ScrapYardPartsId;
import com.app.exception.ResourceNotFoundException;
import com.app.mappers.scrapyard.ScrapYardMapper;
import com.app.repository.ScrapYardPartsRepository;
import com.app.repository.ScrapYardRepository;
import com.app.repository.WriterRepository;


@Service
public class ScrapYardServiceImpl implements ScrapYardService {
	
	private final static String BOOK_NOT_FOUND = "Book with id %d not found";
	private final static String WRITER_NOT_FOUND = "Writer with id %d not found";
	
	@Autowired ScrapYardPartsRepository scrapYardPartsRepository;
	@Autowired ScrapYardRepository scrapYardRepository;
	@Autowired WriterRepository writerRepository;
	@Autowired ScrapYardMapper scrapYardMapper;


	@Override
	public List<ScrapYardPartsResponseDto> getAllPartsBySY(Long scrapyardId) {
		

		List<ScrapYardParts> scrapYardParts =  scrapYardPartsRepository.findByScrapYardId_ScrapYardId(scrapyardId);

		return scrapYardParts.stream()
								.map(scrapYardMapper::toResponse)
								.collect(Collectors.toList());
		
	}

	@Override
	public List<ScrapYardPartsResponseDto> getPartByName(String partName) {
		
		List<ScrapYardParts> parts = scrapYardPartsRepository.findByPartIdNameStartingWith(partName);
		
		return parts.stream()
				.map(scrapYardMapper::toResponse)
				.collect(Collectors.toList());
	}
	
	@Override
	public List<ScrapYardPartsResponseDto> getPartBySubcategoryId(Long partId) {
		List<ScrapYardParts> parts =  scrapYardPartsRepository.findByPartId_PartId(partId);
		return parts.stream()
								.map(scrapYardMapper::toResponse)
								.collect(Collectors.toList());
	}
	
	@Override
	public void putPartToReserved(ScrapYardPartsId idEntity) {
		
        Optional<ScrapYardParts> optional = scrapYardPartsRepository.findById(idEntity);
        
        ScrapYardParts part = optional.get();
        
        if (part.isReserved()) {
            throw new RuntimeException("La pieza ya está reservada.");
        }

        part.setReserved(true);

		ScrapYardParts parts =  scrapYardPartsRepository.save(part);
	}
	
	@Override
	public ScrapYard findById(Long idEntity) {
		
 

		return scrapYardRepository.findById(idEntity)
		        .orElseThrow(() -> new RuntimeException("Desguace no encontrado"));	
	}
	
	@Override
	public void deletePart(ScrapYardRequestDto scrapYardPartDto) {
		
		ScrapYardPartsId scrapYardPart = new ScrapYardPartsId(scrapYardPartDto.getPartId(), scrapYardPartDto.getScrapYardId());
		
        scrapYardPartsRepository.deleteById(scrapYardPart);
	}
	
}
