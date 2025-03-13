package com.scrappinggo.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Data
//@ToString(exclude = "writers")
//@EqualsAndHashCode(exclude = "writers")
@Entity
@Table(name = "parts")
public class Part {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partId;

	@Column(nullable = false, length = 50)
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "car", nullable = true)
	@JsonBackReference
    private Car car;

	@ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			mappedBy = "parts")
    @JsonManagedReference
    private Set<ScrapYard> scrapYards = new LinkedHashSet<>();
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category", nullable = false)
	@JsonManagedReference
    private PartCategory category;
	
}
