package com.scrappinggo.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
//@ToString(exclude = "writers")
//@EqualsAndHashCode(exclude = "writers")
@Entity
@Table(name="scrap_yards")
public class ScrapYard {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrapYardId;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    		)
    @JoinTable(name = "parts_scrap_yard",
		   joinColumns = @JoinColumn(name = "fk_scrap_yard_id"),
		   inverseJoinColumns = @JoinColumn(name = "fk_part_id"))
    @JsonBackReference
    private Set<Part> parts = new LinkedHashSet<>();

	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, length = 50)
	private String location;
}
