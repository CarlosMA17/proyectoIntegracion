package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dtos.categorydto.CategoryResponseDto;
import com.app.entity.PartCategory;
import com.app.entity.ScrapYardParts;
import com.app.mappers.category.CategoryMapper;
import com.app.mappers.scrapyard.ScrapYardMapper;
import com.app.repository.CategoryRepository;
import com.app.repository.ScrapYardRepository;
import com.app.repository.WriterRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired CategoryRepository categoryRepository;
	@Autowired CategoryMapper categoryMapper;
	
	@Override
	public List<CategoryResponseDto> getAllCategoriesByCar(Long carId) {
		List<PartCategory> categories =  categoryRepository.findByCar(carId);
		return categories.stream()
								.map(categoryMapper::toResponse)
								.collect(Collectors.toList());
	}

}
