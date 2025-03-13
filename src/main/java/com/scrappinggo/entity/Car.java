package com.scrappinggo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
//@ToString(exclude = "writers")
//@EqualsAndHashCode(exclude = "writers")
@Entity
@Table(name = "cars")
public class Car {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

	 @OneToMany(
				mappedBy = "car",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	@JsonManagedReference
	private List<Part> parts = new ArrayList<>();

	@Column(nullable = false, length = 50)
	private String brand;
	
	@Column(nullable = false, length = 50)
	private String model;
	
	@Column(nullable = false, length = 50)
	private String engine;
}
