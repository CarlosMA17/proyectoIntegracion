package com.app.entity;

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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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

	@OneToMany(
			mappedBy = "scrapYardId",
		cascade = CascadeType.ALL,
		orphanRemoval = true
		)
	@JsonManagedReference
    private Set<ScrapYardParts> scrapYardParts = new LinkedHashSet<>();

	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, length = 50)
	private String location;
}
