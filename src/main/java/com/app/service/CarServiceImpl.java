package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dtos.carsdtos.CarResponseDto;
import com.app.dtos.carsdtos.MotorResponseDto;
import com.app.entity.Car;
import com.app.mappers.car.CarMapper;
import com.app.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired CarRepository carRepository;
	@Autowired CarMapper carMapper;

	@Override
	public List<CarResponseDto> getAllCars() {
		List<Car> cars =  carRepository.findAll();
		return cars.stream()
				.map(carMapper::toResponse)
				.collect(Collectors.toList());
	}

	@Override
	public List<MotorResponseDto> getAllMotors() {
		// TODO Auto-generated method stub
		return null;
	}
}
