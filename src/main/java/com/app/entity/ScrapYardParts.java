package com.app.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(PartId.class)
public class ScrapYardParts implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name="part_id")
	private Part partId;
	
	@Id
	@ManyToOne
	@JoinColumn(name="scrap_yard_id")
	private ScrapYard scrapYardId;
	
	private int wear_level;
	private float price;
	private boolean stock;
	
	public ScrapYardParts() {}
	
	public ScrapYardParts(Part partId, ScrapYard scrapYardId, int wear_level, float price, boolean stock) {
		super();
		this.partId = partId;
		this.scrapYardId = scrapYardId;
		this.wear_level = wear_level;
		this.price = price;
		this.stock = stock;
	}
}
