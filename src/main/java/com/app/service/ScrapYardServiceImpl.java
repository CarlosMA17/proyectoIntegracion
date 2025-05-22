package com.app.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dtos.partdto.PartResponseDto;
import com.app.dtos.scrapyarddto.ScrapYardPartsResponseDto;
import com.app.dtos.scrapyarddto.ScrapYardRequestDto;
import com.app.dtos.scrapyarddto.ScrapYardResponseDto;
import com.app.dtos.scrapyardpartsdto.ScrapYardPartsRequestDto;
import com.app.entity.Car;
import com.app.entity.Part;
import com.app.entity.PartCategory;
import com.app.entity.Reservation;
import com.app.entity.ScrapYard;
import com.app.entity.ScrapYardParts;
import com.app.exception.ResourceNotFoundException;
import com.app.mappers.scrapyard.ScrapYardMapper;
import com.app.mappers.scrapyardparts.ScrapYardPartsMapper;
import com.app.repository.CarRepository;
import com.app.repository.CategoryRepository;
import com.app.repository.PartRepository;
import com.app.repository.ReservationRepository;
import com.app.repository.ScrapYardPartsRepository;
import com.app.repository.ScrapYardRepository;
import com.app.repository.WriterRepository;

import jakarta.transaction.Transactional;


@Service
public class ScrapYardServiceImpl implements ScrapYardService {
	
	private final static String BOOK_NOT_FOUND = "Book with id %d not found";
	private final static String WRITER_NOT_FOUND = "Writer with id %d not found";
	
	@Autowired ScrapYardPartsRepository scrapYardPartsRepository;
	@Autowired ReservationRepository reservationRepository;
	@Autowired ScrapYardRepository scrapYardRepository;
	@Autowired CategoryRepository categoryRepository;
	@Autowired PartRepository partRepository;
	@Autowired CarRepository carRepository;
	
	@Autowired WriterRepository writerRepository;
	@Autowired ScrapYardPartsMapper scrapYardPartsMapper;
	@Autowired ScrapYardMapper scrapYardMapper;


	@Override
	public List<ScrapYardPartsResponseDto> getAllPartsBySY(Long scrapyardId) {
		

		List<ScrapYardParts> scrapYardParts =  scrapYardPartsRepository.findByScrapYard_ScrapYardId(scrapyardId);

		return scrapYardParts.stream()
								.map(scrapYardPartsMapper::toResponse)
								.collect(Collectors.toList());
		
	}

	@Override
	public List<ScrapYardPartsResponseDto> getPartByName(String partName) {
		
		List<ScrapYardParts> parts = scrapYardPartsRepository.findByPartIdNameStartingWith(partName);
		
		return parts.stream()
				.map(scrapYardPartsMapper::toResponse)
				.collect(Collectors.toList());
	}
	
	@Override
	public List<ScrapYardPartsResponseDto> getPartBySubcategoryIdAndCarId(Long partId, Long carId) {
		List<ScrapYardParts> parts =  scrapYardPartsRepository.findByPartId_PartIdAndCar_CarIdAndReservedFalse(partId, carId);
		return parts.stream()
								.map(scrapYardPartsMapper::toResponse)
								.collect(Collectors.toList());
	}
	
	@Override
	public ScrapYardResponseDto getScrapYardByPartId(Long scrapYardPartId) {
		ScrapYardParts part = scrapYardPartsRepository.findById(scrapYardPartId)
	            .orElseThrow(() -> new RuntimeException("Relación no encontrada"));
		ScrapYard scrapYard = scrapYardRepository.findById(part.getScrapYardId())	            
				.orElseThrow(() -> new RuntimeException("Relación no encontrada"));

	        return scrapYardMapper.toResponse(scrapYard);
	}

	
	@Override
	public ScrapYard findById(Long idEntity) {
		
 

		return scrapYardRepository.findById(idEntity)
		        .orElseThrow(() -> new RuntimeException("Desguace no encontrado"));	
	}
	
	@Override
	public void deletePart(Long scrapYardPartId) {

        scrapYardPartsRepository.deleteById(scrapYardPartId);
	}

	@Override
	public void addPart(ScrapYardPartsRequestDto scrapYardPartRequestDto) {
		
		ScrapYard scrapYard = scrapYardRepository.findById(scrapYardPartRequestDto.getScrapYardId())
			    .orElseThrow(() -> new ResourceNotFoundException("ScrapYard no encontrado"));
		
		Car car = carRepository.findById(scrapYardPartRequestDto.getCarId())
			    .orElseThrow(() -> new ResourceNotFoundException("Car no encontrado"));
		
		Part part = partRepository.findByName(scrapYardPartRequestDto.getPartName())
				.orElseGet(() -> {
					Part newPart = new Part();
					
					PartCategory category = categoryRepository.findById(scrapYardPartRequestDto.getCategoryId())
							.orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
					
					List<Part> parts = car.getParts();
					if (parts == null) {
					    parts = new ArrayList<Part>();
					}
					parts.add(newPart);
					car.setParts(parts);
					
					Set<Car> cars = new LinkedHashSet<>();
					cars.add(car);
					
					
					newPart.setName(scrapYardPartRequestDto.getPartName());
					newPart.setCategory(category);
					newPart.setCars(cars);
					return partRepository.save(newPart);
				});
		
		ScrapYardParts scrapYardPart = scrapYardPartsMapper.toEntity(scrapYardPartRequestDto);
		scrapYardPart.setScrapYardId(scrapYard);
		scrapYardPart.setPartId(part);
		scrapYardPart.setCar(car);
		
        scrapYardPartsRepository.save(scrapYardPart);
	}
	
	@Override
	public void restockScrapYardPart(Long scrapYardPartId) {
		
		
        ScrapYardParts part = scrapYardPartsRepository.findById(scrapYardPartId)
				.orElseThrow(() -> new RuntimeException("pieza no encontrada"));
;
        
                
        if (!part.isReserved()) {
            throw new RuntimeException("La pieza no esta reservada.");
        }

        part.setReserved(false);

		scrapYardPartsRepository.save(part);
		reservationRepository.deleteByScrapYardPart(scrapYardPartId);
		
	}
	
	@Override
	public void updatePart(ScrapYardPartsRequestDto scrapYardPart) {
		
		

        ScrapYardParts part = scrapYardPartsRepository.findById(scrapYardPart.getScrapyardPartId())
				.orElseThrow(() -> new RuntimeException("pieza no encontrada"));
		
        part.setPrice(scrapYardPart.getPrice());
        part.setWearLevel(scrapYardPart.getWearLevel());
        part.setReserved(scrapYardPart.isReserved());
        part.setPartId(partRepository.findByName(scrapYardPart.getPartName()).orElseThrow());
        part.setCar(carRepository.findById(scrapYardPart.getCarId()).orElseThrow());

		scrapYardPartsRepository.save(part);
	}
}
